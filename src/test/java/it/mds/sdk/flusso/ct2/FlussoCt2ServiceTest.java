/* SPDX-License-Identifier: BSD-3-Clause */
 
package it.mds.sdk.flusso.ct2;

import it.mds.sdk.connettore.anagrafiche.tabella.EsitoCDM;
import it.mds.sdk.connettoremds.ConnettoreMds;
import it.mds.sdk.connettoremds.exception.ConnettoreMdsException;
import it.mds.sdk.connettoremds.gaf.webservices.bean.ArrayOfUploadEsito;
import it.mds.sdk.connettoremds.gaf.webservices.bean.ResponseUploadFile;
import it.mds.sdk.flusso.ct2.parser.regole.ParserTracciatoImpl;
import it.mds.sdk.flusso.ct2.parser.regole.RecordDtoCt2;
import it.mds.sdk.flusso.ct2.parser.regole.conf.ConfigurazioneFlussoCt2;
import it.mds.sdk.flusso.ct2.service.FlussoCt2Service;
import it.mds.sdk.flusso.ct2.tracciato.TracciatoSplitterImpl;
import it.mds.sdk.flusso.ct2.tracciato.output.Dataroot;
import it.mds.sdk.gestoreesiti.GestoreRunLog;
import it.mds.sdk.gestoreesiti.conf.Configurazione;
import it.mds.sdk.gestoreesiti.modelli.InfoRun;
import it.mds.sdk.gestoreesiti.modelli.ModalitaOperativa;
import it.mds.sdk.gestoreesiti.validazioneXSD.MainTester;
import it.mds.sdk.gestorefile.GestoreFile;
import it.mds.sdk.gestorefile.exception.XSDNonSupportedException;
import it.mds.sdk.gestorefile.factory.GestoreFileFactory;
import it.mds.sdk.libreriaregole.dtos.RecordDtoGenerico;
import it.mds.sdk.libreriaregole.gestorevalidazione.BloccoValidazione;
import it.mds.sdk.libreriaregole.regole.beans.RegoleFlusso;
import it.mds.sdk.libreriaregole.validator.ValidationEngine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@MockitoSettings(strictness = Strictness.LENIENT)
public class FlussoCt2ServiceTest {

    final String CT2_ACN_CSV = "CT2_ACN.csv";
    private final int dimensioneBlocco = 250000;

    private final String idClient = "";
    private final String periodoRiferimento = "13";
    private final String annoRfierimento = "2022";
    private final String codiceRegione = "080";
    @Autowired
    private ConfigurazioneFlussoCt2 configurazioneFlusso;
    @InjectMocks
    @Spy
    private FlussoCt2Service flusso;
    @Mock
    private RegoleFlusso regoleFlusso;
    @Spy
    private ConfigurazioneFlussoCt2 conf;

    private final ParserTracciatoImpl parser = Mockito.mock(ParserTracciatoImpl.class);
    private final TracciatoSplitterImpl tracciatoSplitter = Mockito.mock(TracciatoSplitterImpl.class);
    private final ConnettoreMds connettoreMds = Mockito.mock(ConnettoreMds.class);
    private final GestoreRunLog grl = Mockito.mock(GestoreRunLog.class);
    private File file = mock(File.class);

    private final ValidationEngine validationEngine = Mockito.mock(ValidationEngine.class);
    private final Dataroot dataroot = Mockito.mock(Dataroot.class);

    private GestoreFile gestoreFile = Mockito.mock(GestoreFile.class);

    private final String idRun = "928";
    private BloccoValidazione bloccoValidazione;

    private final Configurazione config = new Configurazione();
    private final String percorso = String.format("%s/ESITO_%s.json", config.getEsito().getPercorso(), idRun);
    private final String percorsoTemp = String.format("%s/ESITO_%s_TEMP.json", config.getEsito().getPercorso(), idRun);

    private RecordDtoCt2 recordDto;
    private ConfigurazioneFlussoCt2 configurazione;
    private ParserTracciatoImpl parserTracciato = Mockito.mock(ParserTracciatoImpl.class);
    private InfoRun infoRun;
    private MockedStatic<GestoreFileFactory> mockedStatic;

    @BeforeEach
    void init() {
        recordDto = new RecordDtoCt2();

        bloccoValidazione = new BloccoValidazione();
        bloccoValidazione.setNumeroRecord(2);
        bloccoValidazione.setScartati(1);
        bloccoValidazione.setRecordList(List.of(recordDto));


        infoRun = new InfoRun(
                null, null, null, null, null,
                null, null, null, null, null, null,
                1, 1, 1, null, null, null, null,
                null, null, null, null, null, null, null, "nomeFile",
                null, null, null
        );
    }

    @Test
    void validazioneBlocchiTestModalitaT_KO() {

        mockedStatic = mockStatic(GestoreFileFactory.class);

        when(spy(conf.getFlusso())).thenReturn(configurazioneFlusso.getFlusso());
        when(conf.getXmlOutput()).thenReturn(configurazioneFlusso.getXmlOutput());
        List<RecordDtoGenerico> list = List.of(recordDto);
        EsitoCDM esito = new EsitoCDM("abc", "cde");
        List<EsitoCDM> esitoCT2List = List.of(esito);

        given(tracciatoSplitter.creaDataroot(any(), any())).willReturn(dataroot);
        when(flusso.getParserTracciatoImpl()).thenReturn(parserTracciato);
        given(parserTracciato.parseTracciatoBlocco(any(), anyInt(), anyInt())).willReturn(list);

        mockedStatic.when(() -> GestoreFileFactory.getGestoreFile("XML")).thenReturn(gestoreFile);

        willThrow(new XSDNonSupportedException()).given(gestoreFile).scriviDtoFragment(any(), any(), any());

        given(validationEngine.formatJsonEsiti(percorso, percorsoTemp)).willReturn(false);
        given(validationEngine.formatXmlOutput(any(), any(), any())).willReturn(false);
        given(validationEngine.validaFlussoBloccoConRegoleInteroFlussoCT2(anyList(), any(), anyString(), anyList(), anyInt())).willReturn(bloccoValidazione);

        given(grl.getRun(any())).willReturn(infoRun);
        given(grl.cambiaStatoRun(any(), any())).willReturn(infoRun);
        given(grl.updateRun(any())).willReturn(infoRun);

        Assertions.assertThrows(RuntimeException.class,
                () -> this.flusso.validazioneBlocchi(
                        dimensioneBlocco,
                        CT2_ACN_CSV,
                        regoleFlusso,
                        idRun,
                        idClient,
                        ModalitaOperativa.T,
                        periodoRiferimento,
                        annoRfierimento,
                        codiceRegione,
                        grl,
                        esitoCT2List
                ));
        mockedStatic.close();
    }

    @Test
    void validazioneBlocchiTestModalitaT_KO2() {

        mockedStatic = mockStatic(GestoreFileFactory.class);

        when(spy(conf.getFlusso())).thenReturn(configurazioneFlusso.getFlusso());
        when(conf.getXmlOutput()).thenReturn(configurazioneFlusso.getXmlOutput());
        List<RecordDtoGenerico> list = List.of(recordDto);
        EsitoCDM esito = new EsitoCDM("abc", "cde");
        List<EsitoCDM> esitoCT2List = List.of(esito);

        given(tracciatoSplitter.creaDataroot(any(), any())).willReturn(dataroot);
        when(flusso.getParserTracciatoImpl()).thenReturn(parserTracciato);
        given(parserTracciato.parseTracciatoBlocco(any(), anyInt(), anyInt())).willReturn(list);

        mockedStatic.when(() -> GestoreFileFactory.getGestoreFile("XML")).thenReturn(gestoreFile);
        doNothing().when(gestoreFile).scriviDtoFragment(any(), any(), any());

        when(validationEngine.startValidaFlussoBlocco(anyList(), any(), anyString(), any(), anyInt())).thenReturn(bloccoValidazione);
        given(validationEngine.formatJsonEsiti(anyString(), anyString())).willReturn(true);
        given(validationEngine.formatXmlOutput(any(), any(), any())).willReturn(false);
        given(validationEngine.validaFlussoBloccoConRegoleInteroFlussoCT2(anyList(), any(), anyString(), anyList(), anyInt())).willReturn(bloccoValidazione);

        willThrow(new XSDNonSupportedException()).given(validationEngine).puliziaFileCt2(any(), any(), any());

        given(grl.getRun(any())).willReturn(infoRun);
        given(grl.cambiaStatoRun(any(), any())).willReturn(infoRun);
        given(grl.updateRun(any())).willReturn(infoRun);

        this.flusso.validazioneBlocchi(
                dimensioneBlocco,
                CT2_ACN_CSV,
                regoleFlusso,
                idRun,
                idClient,
                ModalitaOperativa.T,
                periodoRiferimento,
                annoRfierimento,
                codiceRegione,
                grl,
                esitoCT2List
        );
        mockedStatic.close();
    }

    @Test
    void validazioneBlocchiTestModalitaT_OK() {

        mockedStatic = mockStatic(GestoreFileFactory.class);
        MainTester mainTester = Mockito.mock(MainTester.class);

        ConfigurazioneFlussoCt2 configurazioneFlussoCt2 = new ConfigurazioneFlussoCt2();
        when(spy(conf.getFlusso())).thenReturn(configurazioneFlussoCt2.getFlusso());
        when(conf.getXmlOutput()).thenReturn(configurazioneFlusso.getXmlOutput());
        List<RecordDtoGenerico> list = List.of(recordDto);
        EsitoCDM esito = new EsitoCDM("abc", "cde");
        List<EsitoCDM> esitoCT2List = List.of(esito);
        given(parser.parseTracciatoBlocco(any(), anyInt(), anyInt())).willReturn(list);

        bloccoValidazione.setRecordList(Collections.emptyList());

        given(parserTracciato.parseTracciatoBlocco(any(), anyInt(), anyInt())).willReturn(list);

        when(validationEngine.startValidaFlussoBlocco(anyList(), any(), anyString(), any(), anyInt())).thenReturn(bloccoValidazione);
        given(validationEngine.formatJsonEsiti(anyString(), anyString())).willReturn(true);
        given(validationEngine.formatXmlOutput(any(), any(), any())).willReturn(false);

        mockedStatic.when(() -> GestoreFileFactory.getGestoreFile("XML")).thenReturn(gestoreFile);
        doNothing().when(gestoreFile).scriviDtoFragment(any(), any(), any());

        given(grl.getRun(any())).willReturn(infoRun);
        given(grl.cambiaStatoRun(any(), any())).willReturn(infoRun);
        given(grl.updateRun(any())).willReturn(infoRun);

        bloccoValidazione.setRecordList(List.of(recordDto));

        when(flusso.getParserTracciatoImpl()).thenReturn(parserTracciato);
        when(flusso.getMainTester()).thenReturn(mainTester);
        when(mainTester.xmlValidationAgainstXSD(any(), any())).thenReturn(true);

        willReturn("nomeFile").given(validationEngine).puliziaFileCt2(any(), any(), any());
        given(validationEngine.validaFlussoBloccoConRegoleInteroFlussoCT2(anyList(), any(), anyString(), anyList(), anyInt())).willReturn(bloccoValidazione);

        this.flusso.validazioneBlocchi(
                dimensioneBlocco,
                CT2_ACN_CSV,
                regoleFlusso,
                idRun,
                idClient,
                ModalitaOperativa.T,
                periodoRiferimento,
                annoRfierimento,
                codiceRegione,
                grl,
                esitoCT2List
        );
        mockedStatic.close();
    }

    @Test
    void validazioneBlocchiTestModalitaKO_ScriviDtoFragment() {

        mockedStatic = mockStatic(GestoreFileFactory.class);

        when(spy(conf.getFlusso())).thenReturn(configurazioneFlusso.getFlusso());
        when(conf.getXmlOutput()).thenReturn(configurazioneFlusso.getXmlOutput());
        List<RecordDtoGenerico> list = List.of(recordDto);
        EsitoCDM esito = new EsitoCDM("abc", "cde");
        List<EsitoCDM> esitoCT2List = List.of(esito);
        when(flusso.getParserTracciatoImpl()).thenReturn(parser);
        given(parser.parseTracciatoBlocco(any(), anyInt(), anyInt())).willReturn(list);

        bloccoValidazione.setRecordList(List.of(recordDto));

        given(validationEngine.startValidaFlussoBlocco(anyList(), any(), anyString(), any(), anyInt())).willReturn(bloccoValidazione);
        given(validationEngine.formatJsonEsiti(any(), any())).willReturn(true);

        willDoNothing().given(flusso).inviaTracciatoMds(any(), any(), any(), any(), any());

        mockedStatic.when(() -> GestoreFileFactory.getGestoreFile("XML")).thenReturn(gestoreFile);
        doThrow(new XSDNonSupportedException()).when(gestoreFile).scriviDtoFragment(eq(null), any(), any());

        given(tracciatoSplitter.creaDataroot(any(), any())).willReturn(null);

        willReturn("nomeFile").given(validationEngine).puliziaFileCt2(any(), any(), any());
        given(validationEngine.validaFlussoBloccoConRegoleInteroFlussoCT2(anyList(), any(), anyString(), anyList(), anyInt())).willReturn(bloccoValidazione);

        given(grl.getRun(any())).willReturn(infoRun);
        given(grl.cambiaStatoRun(any(), any())).willReturn(infoRun);
        given(grl.updateRun(any())).willReturn(infoRun);


        this.flusso.validazioneBlocchi(
                dimensioneBlocco,
                CT2_ACN_CSV,
                regoleFlusso,
                idRun,
                idClient,
                ModalitaOperativa.P,
                periodoRiferimento,
                annoRfierimento,
                codiceRegione,
                grl,
                esitoCT2List
        );
        mockedStatic.close();
    }

    @Test
    void validazioneBlocchiTestModalitaKO_puliziaFile() {

        mockedStatic = mockStatic(GestoreFileFactory.class);

        when(spy(conf.getFlusso())).thenReturn(configurazioneFlusso.getFlusso());
        when(conf.getXmlOutput()).thenReturn(configurazioneFlusso.getXmlOutput());
        List<RecordDtoGenerico> list = List.of(recordDto);
        EsitoCDM esito = new EsitoCDM("abc", "cde");
        List<EsitoCDM> esitoCT2List = List.of(esito);
        when(flusso.getParserTracciatoImpl()).thenReturn(parser);
        given(parser.parseTracciatoBlocco(any(), anyInt(), anyInt())).willReturn(list);

        bloccoValidazione.setRecordList(List.of(recordDto));

        given(validationEngine.validaFlussoBloccoConRegoleInteroFlussoCT2(anyList(), any(), anyString(), anyList(), anyInt())).willReturn(bloccoValidazione);
        given(validationEngine.formatJsonEsiti(any(), any())).willReturn(true);
        willDoNothing().given(flusso).inviaTracciatoMds(any(), any(), any(), any(), any());

        mockedStatic.when(() -> GestoreFileFactory.getGestoreFile("XML")).thenReturn(gestoreFile);
        doNothing().when(gestoreFile).scriviDtoFragment(eq(null), any(), any());

        given(tracciatoSplitter.creaDataroot(any(), any())).willReturn(null);


        willReturn("nomeFile").given(validationEngine).puliziaFileCt2(any(), any(), any());

        given(grl.getRun(any())).willReturn(infoRun);
        given(grl.cambiaStatoRun(any(), any())).willReturn(infoRun);
        given(grl.updateRun(any())).willReturn(infoRun);


        this.flusso.validazioneBlocchi(
                dimensioneBlocco,
                CT2_ACN_CSV,
                regoleFlusso,
                idRun,
                idClient,
                ModalitaOperativa.P,
                periodoRiferimento,
                annoRfierimento,
                codiceRegione,
                grl,
                esitoCT2List
        );
        mockedStatic.close();
    }

    @Test
    void validazioneBlocchiTestModalitaP_OK() {

        when(spy(conf.getFlusso())).thenReturn(configurazioneFlusso.getFlusso());
        when(conf.getXmlOutput()).thenReturn(configurazioneFlusso.getXmlOutput());
        List<RecordDtoGenerico> list = List.of(recordDto);
        EsitoCDM esito = new EsitoCDM("abc", "cde");
        List<EsitoCDM> esitoCT2List = List.of(esito);
        when(flusso.getParserTracciatoImpl()).thenReturn(parser);
        given(parser.parseTracciatoBlocco(any(), anyInt(), anyInt())).willReturn(list);

        bloccoValidazione.setRecordList(List.of(recordDto));

        given(validationEngine.validaFlussoBloccoConRegoleInteroFlussoCT2(anyList(), any(), anyString(), anyList(), anyInt())).willReturn(bloccoValidazione);
        given(validationEngine.formatJsonEsiti(any(), any())).willReturn(true);

        willDoNothing().given(flusso).inviaTracciatoMds(any(), any(), any(), any(), any());

        mockedStatic = mockStatic(GestoreFileFactory.class);

        given(tracciatoSplitter.creaDataroot(any(), any())).willReturn(dataroot);
        mockedStatic.when(() -> GestoreFileFactory.getGestoreFile("XML")).thenReturn(gestoreFile);

        willReturn("nomeFile").given(validationEngine).puliziaFileCt2(any(), any(), any());
        willReturn(true).given(validationEngine).validateXML(any(), any());

        given(grl.getRun(any())).willReturn(infoRun);
        given(grl.cambiaStatoRun(any(), any())).willReturn(infoRun);
        given(grl.updateRun(any())).willReturn(infoRun);


        this.flusso.validazioneBlocchi(
                dimensioneBlocco,
                CT2_ACN_CSV,
                regoleFlusso,
                idRun,
                idClient,
                ModalitaOperativa.P,
                periodoRiferimento,
                annoRfierimento,
                codiceRegione,
                grl,
                esitoCT2List
        );
        mockedStatic.close();
    }

    @Test
    void inviaTracciatoMdsTest_KOMinistero() throws ConnettoreMdsException {
        String nomeFileXml = conf.getXmlOutput().getPercorso() + "SDK_CDM_CT2_" + periodoRiferimento + "_" + idRun +
                ".xml";
        given(grl.getRun(any())).willReturn(infoRun);
        ResponseUploadFile responseUploadFile = new ResponseUploadFile();
        responseUploadFile.setErrorCode("x");
        given(connettoreMds.invioTracciati(any(), any(), any(), any(), any())).willReturn(responseUploadFile);
        given(grl.cambiaStatoRun(any(), any())).willReturn(infoRun);
        flusso.inviaTracciatoMds(
                idRun,
                nomeFileXml,
                grl,
                periodoRiferimento,
                annoRfierimento
        );
    }

    @Test
    void inviaTracciatoMdsTest_ErrorCodeNull() throws ConnettoreMdsException {
        String nomeFileXml = conf.getXmlOutput().getPercorso() + "SDK_CDM_CT2_" + periodoRiferimento + "_" + idRun +
                ".xml";
        given(grl.getRun(any())).willReturn(infoRun);
        ResponseUploadFile responseUploadFile = new ResponseUploadFile();
        responseUploadFile.setErrorCode(null);
        given(connettoreMds.invioTracciati(any(), any(), any(), any(), any())).willReturn(responseUploadFile);
        given(grl.cambiaStatoRun(any(), any())).willReturn(infoRun);
        flusso.inviaTracciatoMds(
                idRun,
                nomeFileXml,
                grl,
                periodoRiferimento,
                annoRfierimento
        );
    }

    @Test
    void inviaTracciatoMdsTest_ListaEsitiNotNull() throws ConnettoreMdsException {
        String nomeFileXml = conf.getXmlOutput().getPercorso() + "SDK_CDM_CT2_" + periodoRiferimento + "_" + idRun +
                ".xml";
        given(grl.getRun(any())).willReturn(infoRun);
        ResponseUploadFile responseUploadFile = new ResponseUploadFile();
        ArrayOfUploadEsito arr = Mockito.mock(ArrayOfUploadEsito.class);

        responseUploadFile.setListaEsitiUpload(arr);
        responseUploadFile.setErrorCode(null);
        given(connettoreMds.invioTracciati(any(), any(), any(), any(), any())).willReturn(responseUploadFile);
        given(grl.cambiaStatoRun(any(), any())).willReturn(infoRun);
        flusso.inviaTracciatoMds(
                idRun,
                nomeFileXml,
                grl,
                periodoRiferimento,
                annoRfierimento
        );
    }

    @Test
    void validaTracciatoTestEsitiCDM() {
        EsitoCDM esito = new EsitoCDM("abc", "cde");
        List<EsitoCDM> esitoCT2List = List.of(esito);

        when(flusso.getParserTracciatoImpl()).thenReturn(parser);
        when(flusso.getFileFromPath(anyString())).thenReturn(file);
        when(file.exists()).thenReturn(true);
        given(parser.parseTracciatoBloccoCT2(any())).willReturn(esitoCT2List);

        flusso.validazioneRegoleInteroFlusso(
                "nomeFile.txt",
                "idRun",
                grl);

    }
}

