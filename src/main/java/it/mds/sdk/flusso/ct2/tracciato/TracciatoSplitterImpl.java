/* SPDX-License-Identifier: BSD-3-Clause */
 
package it.mds.sdk.flusso.ct2.tracciato;

import it.mds.sdk.flusso.ct2.parser.regole.RecordDtoCt2;
import it.mds.sdk.flusso.ct2.parser.regole.conf.ConfigurazioneFlussoCt2;
import it.mds.sdk.flusso.ct2.tracciato.output.Dataroot;
import it.mds.sdk.libreriaregole.tracciato.TracciatoSplitter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.net.URL;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component("tracciatoSplitter")
public class TracciatoSplitterImpl implements TracciatoSplitter<RecordDtoCt2> {

    private static final String XML_FILENAME_TEMPLATE = "SDK_CDM_CT2_%s_%s.xml" ;
    @Override
    //TODO probabilmente va eliminato
    public List<Path> dividiTracciato(Path tracciato) {
        return Collections.emptyList();
    }

    @Override
    public List<Path> dividiTracciato(List<RecordDtoCt2> records, String idRun) {

        ConfigurazioneFlussoCt2 conf = new ConfigurazioneFlussoCt2();
        String fileName = String.format(XML_FILENAME_TEMPLATE,records.get(0).getCampiInput().getPeriodoRiferimentoInput(), idRun);
        Path xml = Path.of(conf.getXmlOutput().getPercorso(),fileName);


        try {
            URL urlXsd = this.getClass().getClassLoader().getResource("CT2.xsd");
            log.debug("URL dell'XSD per la validazione idrun {} : {}", idRun, urlXsd);


            return List.of(xml);


        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            log.error("[{}].dividiTracciato  - records[{}]  - idRun[{}] -" + e.getMessage(),
                    this.getClass().getName(),
                    e
            );
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Impossibile validare il csv in ingresso. message: " + e.getMessage());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private Dataroot.REGIONE creaRegione(String codiceRegione) {
        Dataroot.REGIONE regioneEr = new Dataroot.REGIONE();
        regioneEr.setCodReg(codiceRegione);
        return regioneEr;
    }

    private Dataroot.REGIONE.AS creaAs(String codiceAs) {
        Dataroot.REGIONE.AS as = new Dataroot.REGIONE.AS();
        as.setCodAs(codiceAs);
        return as;
    }

    private Dataroot.REGIONE.AS.OPERAZIONE creaOperazione(String operazione) {
        Dataroot.REGIONE.AS.OPERAZIONE op = new Dataroot.REGIONE.AS.OPERAZIONE();
        op.setTipoOp(operazione);
        return op;
    }

    private Dataroot.REGIONE.AS.OPERAZIONE.CONTRATTO creaContratto(String numeroContratto, String tipoContratto, String durata, String formaNeg,
                                                                   String ambVal, String anno, String mese, String giorno, String codCig) {
        Dataroot.REGIONE.AS.OPERAZIONE.CONTRATTO contratto = new Dataroot.REGIONE.AS.OPERAZIONE.CONTRATTO();
        contratto.setNumContr(numeroContratto);
        contratto.setTipoContr(tipoContratto);
        contratto.setDurataContr(durata!=null ? Integer.parseInt(durata) : 0);
        contratto.setFormaNeg(formaNeg);
        contratto.setAmbVal(ambVal);
        contratto.setAnno(String.valueOf(anno));
        contratto.setMese(mese);
        contratto.setGiorno(giorno);
        contratto.setCodCig(codCig);
        return contratto;
    }

    private Dataroot.REGIONE.AS.OPERAZIONE.CONTRATTO.DISPOSITIVO creaDispositivo(String tipoDisp, String numRep, String numPez, String denForn, String pIva,
                                                                                 String qtaAgg, String qtaContr, String prezzoAgg, String iva,
                                                                                 String flgSerAcc, String flgContoDep, String codModCe) {
        Dataroot.REGIONE.AS.OPERAZIONE.CONTRATTO.DISPOSITIVO dispositivo = new Dataroot.REGIONE.AS.OPERAZIONE.CONTRATTO.DISPOSITIVO();
        dispositivo.setTipoDispositivo(tipoDisp);
        dispositivo.setNumRep(numRep!=null ? Long.parseLong(numRep): 1);
        dispositivo.setNumPz(numPez!= null ? new BigDecimal(numPez) : BigDecimal.ZERO);
        dispositivo.setDenForn(denForn);
        dispositivo.setPivaForn(pIva);
        dispositivo.setQtaAgg(qtaAgg !=null ? new BigDecimal(qtaAgg): BigDecimal.ZERO);
        dispositivo.setQtaContr(qtaContr!=null ? new BigDecimal(qtaContr): BigDecimal.ZERO);
        dispositivo.setPrezzoAgg(prezzoAgg != null ? new BigDecimal(prezzoAgg): BigDecimal.ZERO);
        dispositivo.setIva(iva);
        dispositivo.setFlgSerAcc(flgSerAcc);
        dispositivo.setFlgContoDep(flgContoDep);
        dispositivo.setCodModCe(codModCe);
        return dispositivo;
    }

    public Dataroot creaDataroot(List<RecordDtoCt2> records, Dataroot dataRoot) {
        if (dataRoot == null) {
            dataRoot = new Dataroot();
            //imposto la regione che è unica per il file
            Dataroot.REGIONE regione = creaRegione(records.get(0).getCodiceRegione());
            //imposto il periodo che è unico per il file
            dataRoot.setREGIONE(regione);
        }
        for (RecordDtoCt2 r : records) {
            // AS
            Optional<Dataroot.REGIONE.AS> currentAS = dataRoot.getREGIONE().getAS()
                    .stream()
                    .filter(as -> Objects.equals(r.getCodiceAziendaSanitariaContraente(), as.getCodAs()))
                    .findFirst();

            if (currentAS.isEmpty()) {
                currentAS = Optional.of(creaAs(r.getCodiceAziendaSanitariaContraente()));
                dataRoot.getREGIONE().getAS().add(currentAS.get());
            }
            // Operazione
            Optional<Dataroot.REGIONE.AS.OPERAZIONE> currentOperazione = currentAS.get().getOPERAZIONE()
                    .stream()
                    .filter(s -> (Objects.equals(r.getTipoOperazione(), s.getTipoOp()))
                    ).findFirst();

            if (currentOperazione.isEmpty()) {
                currentOperazione = Optional.of(creaOperazione(r.getTipoOperazione()));
                currentAS.get().getOPERAZIONE().add(currentOperazione.get());
            }
            // Contratto
            Optional<Dataroot.REGIONE.AS.OPERAZIONE.CONTRATTO> currentContratto = currentOperazione.get().getCONTRATTO()
                    .stream()
                    .filter(s -> Objects.equals(r.getIdentificativoDelContratto(), s.getNumContr()) &&
                            Objects.equals(r.getTipologiaDiContratto(), s.getTipoContr()) &&
                            Objects.equals(r.getDurataDelContratto(), String.valueOf(s.getDurataContr())) &&
                            Objects.equals(r.getFormaDiNegoziazione(), s.getFormaNeg()) &&
                            Objects.equals(r.getAmbitoDiValenzaDelContratto(), s.getAmbVal()) &&
                            Objects.equals(r.getAnnoStipulaContratto(), s.getAnno()) &&
                            Objects.equals(r.getMeseStipulaContratto(), s.getMese()) &&
                            Objects.equals(r.getGiornoStipulaContratto(), s.getGiorno()) &&
                            Objects.equals(r.getCodiceCig(), s.getCodCig())
                    ).findFirst();

            if (currentContratto.isEmpty()) {
                currentContratto = Optional.of(creaContratto(r.getIdentificativoDelContratto(), r.getTipologiaDiContratto(),
                        r.getDurataDelContratto() , r.getFormaDiNegoziazione(), r.getAmbitoDiValenzaDelContratto(),
                        r.getAnnoStipulaContratto(), r.getMeseStipulaContratto(),
                        r.getGiornoStipulaContratto(), r.getCodiceCig()));
                currentOperazione.get().getCONTRATTO().add(currentContratto.get());
            }
            // Dispositivo
            Optional<Dataroot.REGIONE.AS.OPERAZIONE.CONTRATTO.DISPOSITIVO> currentDispositivo = currentContratto.get().getDISPOSITIVO()
                    .stream()
                    .filter(s -> Objects.equals(r.getTipoDispositivoMedico(), s.getTipoDispositivo()) &&
                            Objects.equals(r.getIdentificativoDiIscrizioneInBancaDatiRepertorio(), s.getNumRep()) &&
                            Objects.equals(r.getNumeroDiPezziPresentiNellaConfezioneMinimaDiVendita(), s.getNumPz()) &&
                            Objects.equals(r.getDenominazioneDelFornitore(), s.getDenForn()) &&
                            Objects.equals(r.getPartitaIvaDelFornitore(), s.getPivaForn()) &&
                            Objects.equals(r.getQuantitaAggiudicata(), s.getQtaAgg()) &&
                            Objects.equals(r.getQuantitaContrattualizzata(), s.getQtaContr()) &&
                            Objects.equals(r.getPrezzoUnitarioAggiudicato(), s.getPrezzoAgg()) &&
                            Objects.equals(r.getAliquotaIva(), s.getIva()) &&
                            Objects.equals(r.getServiziAccessori(), s.getFlgSerAcc()) &&
                            Objects.equals(r.getContoDeposito(), s.getFlgContoDep()) &&
                            Objects.equals(r.getVoceDiImputazioneNelModelloCE(), s.getCodModCe())
                    ).findFirst();

            if (currentDispositivo.isEmpty()) {
                currentDispositivo = Optional.of(
                        creaDispositivo(r.getTipoDispositivoMedico(), r.getIdentificativoDiIscrizioneInBancaDatiRepertorio() , r.getNumeroDiPezziPresentiNellaConfezioneMinimaDiVendita(),
                                r.getDenominazioneDelFornitore(), r.getPartitaIvaDelFornitore(), r.getQuantitaAggiudicata(),
                                r.getQuantitaContrattualizzata(), r.getPrezzoUnitarioAggiudicato(), String.valueOf(r.getAliquotaIva()),
                                r.getServiziAccessori(), r.getContoDeposito(), r.getVoceDiImputazioneNelModelloCE()
                        )
                );
                currentContratto.get().getDISPOSITIVO().add(currentDispositivo.get());
            }
        }
        return dataRoot;
    }
}
