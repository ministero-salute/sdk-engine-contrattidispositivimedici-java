/* SPDX-License-Identifier: BSD-3-Clause */
 
package it.mds.sdk.flusso.ct2;

import it.mds.sdk.connettore.anagrafiche.gestore.anagrafica.CacheSQLite;
import it.mds.sdk.connettore.anagrafiche.sqlite.QueryAnagrafica;
import it.mds.sdk.flusso.ct2.parser.regole.ParserTracciatoImpl;
import it.mds.sdk.flusso.ct2.parser.regole.RecordDtoCt2;
import it.mds.sdk.flusso.ct2.tracciato.TracciatoSplitterImpl;
import it.mds.sdk.flusso.ct2.tracciato.output.Dataroot;
import it.mds.sdk.libreriaregole.dtos.CampiInputBean;
import it.mds.sdk.libreriaregole.dtos.RecordDtoGenerico;
import it.mds.sdk.libreriaregole.parser.ParserTracciato;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ParserTracciatoImplTest {

    private static final String FILE_TRACCIATO_CT2 = "CT2.csv";

    MockedStatic<CacheSQLite> cacheSQLiteMockedStatic;

    MockedStatic<QueryAnagrafica> queryAnagraficaMockedStatic;

    QueryAnagrafica queryAnagrafica = Mockito.mock(QueryAnagrafica.class);

    CacheSQLite cacheSQLite = new CacheSQLite();
    String pathFileCsv;
    String pathFileCsv1000;
    File fileCsv;
    File fileCsv1000;
    private int dimensioneBlocco = 300;

    @BeforeEach
    void init() {
        queryAnagraficaMockedStatic = mockStatic(QueryAnagrafica.class);
        cacheSQLiteMockedStatic = mockStatic(CacheSQLite.class);
        Properties prop = loadPropertiesFromFile("config-flusso-ct2-test.properties");
        this.pathFileCsv = prop.getProperty("test.filecsv");
        this.pathFileCsv1000 = prop.getProperty("test.filecsv1000");

        ClassLoader classLoader = getClass().getClassLoader();
        fileCsv = new File(Objects.requireNonNull(classLoader.getResource(pathFileCsv)).getFile());
        fileCsv1000 = new File(Objects.requireNonNull(classLoader.getResource(pathFileCsv1000)).getFile());
        System.out.println("test init");
    }

    @Test
    void getInstanceTest() {
        queryAnagraficaMockedStatic.when(CacheSQLite::getInstance).thenReturn(cacheSQLite);
        CacheSQLite.getInstance();
    }

    @Test
    void validaTracciatoOK() {
        ParserTracciato parserTracciato = new ParserTracciatoImpl();
        Path resourceDirectory = Paths.get("src", "test", "resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();
        File tracciato = new File(absolutePath + FileSystems.getDefault().getSeparator() + FILE_TRACCIATO_CT2);

        List<RecordDtoGenerico> listaRecord = parserTracciato.parseTracciato(tracciato);
        listaRecord.forEach(System.out::println);
        assertFalse(ArrayUtils.isEmpty(listaRecord.toArray()));
    }

    @Test
    void validaTracciatoBloccoCT2() throws SQLException {
        ParserTracciatoImpl parserTracciato = new ParserTracciatoImpl();
        Path resourceDirectory = Paths.get("src", "test", "resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();
        File tracciato = new File(absolutePath + FileSystems.getDefault().getSeparator() + FILE_TRACCIATO_CT2);
        cacheSQLiteMockedStatic.when(CacheSQLite::getInstance).thenReturn(cacheSQLite);
        queryAnagraficaMockedStatic.when(QueryAnagrafica::getInstanceWithCache).thenReturn(queryAnagrafica);
        Mockito.when(queryAnagrafica.creaTabellaBR3060(any())).thenReturn(Boolean.TRUE);
        Mockito.when(queryAnagrafica.dropTable(any())).thenReturn(Boolean.TRUE);
        parserTracciato.parseTracciatoBloccoCT2(tracciato);
    }

    @Test
    void integBLocco() {
        ParserTracciatoImpl p = new ParserTracciatoImpl();
        List<RecordDtoGenerico> list = p.parseTracciatoBlocco(fileCsv, 1, dimensioneBlocco);
        CampiInputBean c = new CampiInputBean("13", "2022", "120");

        for (RecordDtoGenerico r : list) {
            r.setCampiInput(c);
        }
        List<RecordDtoCt2> r2 = list.stream().map(k -> (RecordDtoCt2) k).collect(Collectors.toList());
        TracciatoSplitterImpl splitter = new TracciatoSplitterImpl();
        splitter.dividiTracciato(r2, "34");
        System.out.println(list.size());

    }

    @Test
    void scritturaNBlocchiSuXML() {
        ParserTracciatoImpl p = new ParserTracciatoImpl();

        //Prova blocchi
        List<RecordDtoGenerico> blocco = p.parseTracciatoBlocco(fileCsv1000, 1, dimensioneBlocco);
        List<RecordDtoCt2> bloccoConv = blocco.stream().map(k -> (RecordDtoCt2) k).collect(Collectors.toList());

        TracciatoSplitterImpl t = new TracciatoSplitterImpl();
        Dataroot dataroot = t.creaDataroot(bloccoConv, null);

        System.out.println("blocco 1");
        blocco = p.parseTracciatoBlocco(fileCsv1000, dimensioneBlocco + 1, dimensioneBlocco + 300);
        bloccoConv = blocco.stream().map(k -> (RecordDtoCt2) k).collect(Collectors.toList());
        dataroot = t.creaDataroot(bloccoConv, dataroot);
    }

    private Properties loadPropertiesFromFile(String fileName) {
        Properties prop = new Properties();
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream stream = loader.getResourceAsStream(fileName);
            prop.load(stream);
            stream.close();
        } catch (Exception e) {
            String msg = String.format("Failed to load file '%s' - %s - %s", fileName, e.getClass().getName(),
                    e.getMessage());
            System.out.println(msg);
        }
        return prop;
    }

    @AfterEach
    void closeStaticMocks() {
        queryAnagraficaMockedStatic.close();
        cacheSQLiteMockedStatic.close();
    }
}