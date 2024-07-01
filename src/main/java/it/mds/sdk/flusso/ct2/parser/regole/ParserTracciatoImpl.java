/* SPDX-License-Identifier: BSD-3-Clause */
 
package it.mds.sdk.flusso.ct2.parser.regole;


import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import it.mds.sdk.connettore.anagrafiche.gestore.anagrafica.CacheSQLite;
import it.mds.sdk.connettore.anagrafiche.tabella.EsitoCDM;
import it.mds.sdk.connettore.anagrafiche.tabella.RecordCDM;
import it.mds.sdk.connettore.anagrafiche.tabella.RecordCDMCT2;
import it.mds.sdk.connettore.anagrafiche.tabella.TabellaAnagrafica;
import it.mds.sdk.flusso.ct2.parser.regole.conf.ConfigurazioneFlussoCt2;
import it.mds.sdk.libreriaregole.dtos.RecordDtoGenerico;
import it.mds.sdk.libreriaregole.parser.ParserTracciato;
import it.mds.sdk.rest.exception.ParseCSVException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Component("parserTracciato")
public class ParserTracciatoImpl implements ParserTracciato {

    private final int CHUNK_SIZE_CT2 = 100000;
    private ConfigurazioneFlussoCt2 conf = new ConfigurazioneFlussoCt2();


    /**
     * Il metodo converte un File.csv in una List<RecordDtoGenerico> usando come separatore "~"
     *
     * @param tracciato, File.csv di input
     * @return una lista di RecordDtoDir
     */

    @Override
    public List<RecordDtoGenerico> parseTracciato(File tracciato) {
        char separatore = conf.getSeparatore().getSeparatore().charAt(0);
        try(FileReader fileReader = new FileReader(tracciato)) {
            List<RecordDtoGenerico> dirList = new CsvToBeanBuilder<RecordDtoGenerico>(fileReader)
                    .withType(RecordDtoCt2.class)
                    .withSeparator(separatore)
                    .withSkipLines(1)   //Salta la prima riga del file CSV
                    .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS)
                    .build()
                    .parse();

            return dirList;

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new ParseCSVException(ex.getMessage());
        }

        return Collections.emptyList();
    }

    public List<EsitoCDM> parseTracciatoBloccoCT2(File tracciato) {

        log.info("Inizio parse tracciato file {} scrittura, filtraggio record e drop table", tracciato.getName());

        StopWatch stopWatch = new StopWatch();
        String nomeTabella = "CT2";

        log.info("Inizio lettura file {} e scrittura nella tabella {}", tracciato.getName(), nomeTabella);

        stopWatch.start();

        try (Reader reader = Files.newBufferedReader(tracciato.toPath())) {

            Iterator<RecordDtoCt2> it = new CsvToBeanBuilder<RecordDtoCt2>(reader)
                    .withType(RecordDtoCt2.class)
                    .withSeparator('~')
                    .withSkipLines(1)   //Salta la prima riga del file CSV
                    .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS)
                    .build()
                    .iterator();

            int count = 0;
            int idRecord = 0;
            List<RecordCDMCT2> list = new ArrayList<>();

            CacheSQLite cacheSQLite = CacheSQLite.getInstance();
            cacheSQLite.creaTabellaCT2(nomeTabella);

            while (it.hasNext()) {
                count++;
                idRecord++;
                RecordDtoGenerico recordGen = it.next();
                RecordCDMCT2 recordCt2 = createRecordCT2FromRecordGenerico(recordGen, idRecord);

                list.add(recordCt2);

                if (count == CHUNK_SIZE_CT2 || !it.hasNext()) {
                    count = 0;
                    TabellaAnagrafica<RecordCDMCT2> tabellaAnagraficaCT2 = new TabellaAnagrafica<>();
                    tabellaAnagraficaCT2.setRecordsAnagrafica(list);
                    tabellaAnagraficaCT2.setNome(nomeTabella);

                    cacheSQLite.addRecordCT2(tabellaAnagraficaCT2);
                    list.clear();
                }
            }

            log.info("Fine lettura file {} e scrittura nella tabella {}", tracciato.getName(), nomeTabella);
            log.info("Inizio filtraggio elementi scartati dalla tabella {}", nomeTabella);
            TabellaAnagrafica<EsitoCDM> tabellaAnagraficaCT2 = new TabellaAnagrafica<>();
            tabellaAnagraficaCT2.setNome(nomeTabella);

            var listaScartati = cacheSQLite.selectFilterCT2(tabellaAnagraficaCT2);
            log.info("Fine filtraggio elementi scartati dalla tabella {}", nomeTabella);
            if (!cacheSQLite.dropTableCT2(nomeTabella)) {
                log.debug("La Tabella {} non è stata droppata.", nomeTabella);
                throw new SQLException();
            }
            stopWatch.stop();
            log.info("Fine parse tracciato file {} scrittura, filtraggio record e drop table {} in {} ms", tracciato.getName(), nomeTabella, stopWatch.getLastTaskTimeMillis());

            return listaScartati;

        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new ParseCSVException(ex.getMessage());
        }
    }

    public List<RecordDtoGenerico> parseTracciatoBlocco(File tracciato, int inizio, int fine) {
        StopWatch stopWatch = new StopWatch();
        char separatore = conf.getSeparatore().getSeparatore().charAt(0);
        log.info("Inizio lettura file {} da riga {} a riga {}", tracciato.getName(), inizio, fine);
        stopWatch.start();
        try (Reader reader = Files.newBufferedReader(tracciato.toPath())) {

            List<RecordDtoGenerico> res = new ArrayList<>();
            Iterator<RecordDtoCt2> it = new CsvToBeanBuilder<RecordDtoCt2>(reader)
                    .withType(RecordDtoCt2.class)
                    .withSeparator(separatore)
                    .withSkipLines(inizio + 1)   //Salta la prima riga del file CSV
                    .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS)
                    .build()
                    .iterator();

            int count = inizio;
            int totaleRecordElaborati = 0;

            while (it.hasNext() && count < fine) {
                count++;
                RecordDtoGenerico recordGen = it.next();
                res.add(recordGen);
                totaleRecordElaborati++;
            }

            stopWatch.stop();
            log.info("Fine lettura file {} da riga {} a riga {} con {} record in {} ms", tracciato.getName(), inizio,
                    fine, totaleRecordElaborati, stopWatch.getLastTaskTimeMillis());

            return res;
        } catch (IOException e) {
            throw new ParseCSVException(e.getMessage());
        } catch (Exception ex) {
            throw new ParseCSVException(ex.getMessage());
        }
    }

    private RecordCDMCT2 createRecordCT2FromRecordGenerico(RecordDtoGenerico recordGen, int idRecord) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        log.debug("{}.createRecordCT2FromRecordGenerico - Mapping record {} ", this.getClass().getName(), idRecord);
        return new RecordCDMCT2(
                idRecord,
                (String) recordGen.getCampo("codiceAziendaSanitariaContraente"),
                (String) recordGen.getCampo("identificativoDelContratto"),
                (String) recordGen.getCampo("tipoDispositivoMedico"),
                (String) recordGen.getCampo("voceDiImputazioneNelModelloCE"),
                (String) recordGen.getCampo("progressivoRiga")
        );
    }
}
