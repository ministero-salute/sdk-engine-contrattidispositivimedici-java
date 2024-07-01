# **1 Introduzione**

## ***1.1 Obiettivi del documento***

Il Ministero della Salute (MdS) metterà a disposizione degli Enti, da cui riceve dati,  applicazioni SDK specifiche per flusso logico e tecnologie applicative (Java, PHP e C#) per verifica preventiva (in casa Ente) della qualità del dato prodotto.

![](img/Aspose.Words.15dc6452-d384-4da1-98ac-525688916e4b.002.png)

Nel presente documento sono fornite la struttura e la sintassi dei tracciati previsti dalla soluzione SDK per avviare il proprio processo elaborativo e i controlli di merito sulla qualità, completezza e coerenza dei dati.

Gli obiettivi del documento sono:

- fornire una descrizione funzionale chiara e consistente dei tracciati di input a SDK;
- fornire le regole funzionali per la verifica di qualità, completezza e coerenza dei dati.

In generale, la soluzione SDK è costituita da 2 diversi moduli applicativi (Access Layer e Validation Engine) per abilitare:

- l’interoperabilità con il contesto tecnologico dell’Ente in cui la soluzione sarà installata;
- la validazione del dato ed il suo successivo invio verso il MdS.

La figura che segue descrive la soluzione funzionale ed i relativi benefici attesi.

![](img/Aspose.Words.15dc6452-d384-4da1-98ac-525688916e4b.003.png)

## ***1.2 Acronimi***

Nella tabella riportata di seguito sono elencati tutti gli acronimi e le definizioni adottate nel presente documento.


|**#**|**Acronimo / Riferimento**|**Definizione**|
| - | - | - |
|1|AO|Azienda ospedaliera|
|2|AOU|Azienda Ospedaliera Universitaria|
|3|ASL|Azienda sanitaria locale|
|4|CSM|Centro Salute Mentale|
|5|ESTAV|Enti per i Servizi Tecnico-amministrativi di Area Vasta|
|6|IRCCS|Istituto di ricovero e cura a carattere scientifico|
|7|MRA|Monitoraggio della Rete di Assistenza|
|8|RDM|Repertorio dei Dispositivi Medici|
|9|RSA|Residenza Sanitaria Assistenziale|
|10|SERT|Servizi per le Tossicodipendenze|
|11|SSN|Servizio Sanitario Nazionale|
|12|SDK|Software Development Kit|
|13|XML|eXtensible Markup Language|
|14|XSD|XML Schema Definition|


# **2 Architettura SDK**

## ***2.1 Architettura funzionale***

Di seguito una rappresentazione architetturale del processo di gestione e trasferimento dei flussi dall’ente verso l’area MdS attraverso l’utilizzo dell’applicativo SDK, e il corrispondente diagramma di sequenza.

![DiagramDescription automatically generated](img/Aspose.Words.15dc6452-d384-4da1-98ac-525688916e4b.004.jpeg)

![A picture containing graphical user interfaceDescription automatically generated](img/Aspose.Words.15dc6452-d384-4da1-98ac-525688916e4b.005.png)

1. L’utente dell’ente caricherà in una apposita directory (es. /sdk/input/) il flusso sorgente.  L’utente avvierà l’SDK passando in input una serie di parametri descritti in dettaglio al paragrafo 3.1
1. La componente Access Layer estrae dalla chiamata dell’ente i parametri utilizzati per lanciare l’SDK,  genera un identificativo ID\_RUN, e un file chiamato “{ID\_RUN}.json” in cui memorizza le informazioni dell’esecuzione.
1. I record del flusso verranno sottoposti alle logiche di validazione e controllo definite nel Validation Engine. Nel processare il dato, il Validation Engine acquisirà da MdS eventuali anagrafiche di validazione del dato stesso.
1. Generazione del file degli scarti contenente tutti i record in scarto con evidenza degli errori riscontrati. I file di scarto saranno memorizzati in cartelle ad hoc (es. /sdk/esiti).
1. Tutti i record che passeranno i controlli verranno inseriti in un file xml copiato in apposita cartella (es /sdk/xml\_output), il quale verrà eventualmente trasferito a MdS utilizzando la procedura “invioFlussi” esposta da GAF WS (tramite PDI). A fronte di un’acquisizione, il MdS fornirà a SDK un identificativo (ID\_UPLOAD) che sarà usato da SDK sia per fini di logging che di recupero del File Unico degli Scarti.
1. A conclusione del processo di verifica dei flussi, il Validation Engine eseguirà le seguenti azioni:

 a. Aggiornamento file contentente il riepilogo dell’esito dell’elaborazione del Validation Engine e del ritorno dell’esito da parte di MdS. I file contenente l’esito dell’elaborazione saranno memorizzati in cartelle ad hoc (es. /sdk/run).

 b. Consolidamento del file di log applicativo dell’elaborazione dell’SDK in cui sono disponibili una serie di informazioni tecniche (Es. StackTrace di eventuali errori).

 c. Copia del file generato al punto 5, se correttamente inviato al MdS, in apposita cartella (es. /sdk/sent).

Ad ogni step del precedente elenco e a partire dal punto 2, l’SDK aggiornerà di volta in volta il file contenente l’esito dell’elaborazione.

**Nota: l’SDK elaborerà un solo file di input per esecuzione.**

In generale, le classi di controllo previste su Validation Engine sono:

- Controlli FORMALI (es. correttezza dei formati e struttura record)
- Controlli SINTATTICI (es. check correttezza del Codice Fiscale)
- Controlli di OBBLIGATORIETÀ DEL DATO (es. Codice Prestazione su flusso…)
- Controlli STRUTTURE FILE (es. header/footer ove applicabile)
- Controlli di COERENZA CROSS RECORD
- Controlli di corrispondenza dei dati trasmessi con le anagrafiche di riferimento;
- Controlli di esistenza di chiavi duplicate nel file trasmesso rispetto alle chiavi logiche individuate per ogni tracciato.

Si sottolinea che la soluzione SDK non implementa controlli che prevedono la congruità del dato input con la base date storica (es non viene verificato se per un nuovo inserimento (Tipo = I) la chiave del record non sia già presente sulla struttura dati MdS).

## ***2.2 Architettura di integrazione***

La figura sottostante mostra l’architettura di integrazione della soluzione SDK con il MdS. Si evidenzia in particolare che:

- Tutti i dati scambiati fra SDK e MdS saranno veicolati tramite Porta di Interoperabilità (PDI);
- Il MdS esporrà servizi (API) per il download di dati anagrafici;
- SDK provvederà ad inviare vs MdS l’output (record validati) delle proprie elaborazioni. A fronte di tale invio, il MdS provvederà a generare un identificativo di avvenuta acquisizione del dato (ID\_UPLOAD) che SDK memorizzerà a fini di logging.


![](img/Aspose.Words.15dc6452-d384-4da1-98ac-525688916e4b.006.png)


# **3 Funzionamento della soluzione SDK**

In questa sezione è descritta le specifica di funzionamento del flusso **CT2**  per l’alimentazione dello stesso.


## ***3.1 Input SDK***

In fase di caricamento del file verrano impostati i seguenti parametri che andranno in input al SDK in fase di processamento del file:


|**NOME PARAMETRO**|**DESCRIZIONE**|**LUNGHEZZA**|**DOMINIO VALORI**|
| :- | :- | :- | :- |
|ID CLIENT|Identificativo univoco della trasazione che fa richiesta all'SDK|100|Non definito|
|NOME FILE INPUT|Nome del file per il quale si richiede il processamento lato SDK|256|Non definito|
|ANNO RIFERIMENTO|Stringa numerica rappresentante l’anno di riferimento per cui si intende inviare la fornitura|4|Anno (Es. 2022)|
|PERIODO RIFERIMENTO|Stringa alfanumerica rappresentante il periodo per il quale si intende inviare la fornitura|2|**13** (rappresenta un alias per il quale MDS usa come data di competenza l’anno e il mese di riferimento del record piuttosto che il parametro periodo di riferimento passato in input alla procedura InvioFlussi)|
|TIPO TRASMISSIONE |Indica se la trassmissione dei dati verso MDS avverrà il modalità full (F) o record per record (R). Per questo flusso la valorizzazione del parametro sarà impostata di default a F|1|F/R|
|FINALITA' ELABORAZIONE|Indica se i flussi in output prodotti dal SDK verranno inviati verso MDS (Produzione) oppure se rimarranno all’interno del SDK e il processamento vale solo come test del flusso (Test)|1|Produzione/Test|
|CODICE REGIONE|<p>Individua la Regione a cui afferisce la struttura. Il codice da utilizzare è quello a tre caratteri definito con DM 17 settembre 1986, pubblicato nella Gazzetta Ufficiale n.240 del 15 ottobre 1986, e successive modifiche, utilizzato anche nei modelli per le rilevazioni delle attività gestionali ed economiche delle Aziende unità sanitarie locali.</p><p></p>|3|Es. 010|

## ***3.2 Tracciato input a SDK***

Il flusso di input avrà formato **csv** posizionale e una naming convention libera a discrezione dell’utente che carica il flusso senza alcun vincolo di nomenclatura specifica (es nome\_file.csv). Il separatore per il file csv sarà la combinazione di caratteri tra doppi apici: “~“

All’interno delle specifiche dei tracciati riportati nei paragrafi successivi sono indicati i dettagli dei campi di business del tracciato di input atteso da SDK, il quale differisce per i flussi dell’area CDM. All’interno di tale file è presente la colonna **Posizione nel file** la quale rappresenta l’ordinamento delle colonne del tracciato di input da caricare all’SDK.

Di seguito la tabella in cui è riportata la specifica del tracciato di input per il flusso in oggetto:

|**Nome campo**|**Posizione nel File**|**Key**|**Descrizione**|**Tipo** |**Obbligatorietà**|**Informazioni di Dominio**|**Lunghezza campo**|**XPATH Tracciato Output**|
| :-: | :- | :- | :-: | :-: | :-: | :-: | :-: | :-: |
|Codice Regione|1|KEY|Codice della Regione territorialmente competente|AN|OBB|Valori di riferimento riportati nell’Allegato A|3|/dataroot/@xsi/REGIONE/@cod_reg|
|Codice Azienda Sanitaria contraente|2|KEY|Codice che identifica l’azienda sanitaria contraente (Azienda sanitaria locale o equiparata, Azienda Ospedaliera, Istituto di Ricovero e Cura a Carattere scientifico pubblico anche se trasformato in fondazione, Azienda Ospedaliera Universitaria integrata con il SSN)|AN|OBB|Per AO, IRCCS e AOU indicare il codice del modello HSP11, per le altre indicare il codice del modello FLS.11 (ASL, ESTAV)|6|/dataroot/@xsi/REGIONE/AS/@cod_as|
|Identificativo del contratto|3|KEY|Codice che identifica in modo univoco il contratto di acquisizione dei dispositivi medici con riferimento alla Regione – Azienda Sanitaria Contraente. Il campo può contenere il numero di riferimento interno attribuito dal Sistema Informativo della Regione – Azienda Sanitaria per identificare univocamente il contratto e/o il singolo dispositivo.|AN|OBB|Campo alfanumerico. Il carattere @ è riservato dunque non utilizzabile.|40|/dataroot/@xsi/REGIONE/AS/OPERAZIONE/CONTRATTO/@num_contr|
|Tipologia di contratto|4| |Tipologia di contratto ai sensi della normativa vigente (Codice civile e Codice degli appalti)|AN|FAC|Valori di riferimento riportati nell’Allegato B-2|2|/dataroot/@xsi/REGIONE/AS/OPERAZIONE/CONTRATTO/@tipo_contr|
|Anno stipula contratto|5| |Anno in cui è stipulato il contratto|N|OBB|Anno nel formato AAAA|4|/dataroot/@xsi/REGIONE/AS/OPERAZIONE/CONTRATTO/@anno|
|Mese stipula contratto|6| |Mese in cui è stipulato il contratto|AN|OBB|Mese nel formato MM (da 01 a 12)|2|/dataroot/@xsi/REGIONE/AS/OPERAZIONE/CONTRATTO/@mese|
|Giorno stipula contratto|7| |Giorno in cui è stipulato il contratto|AN|OBB|Giorno nel formato GG (da 01 a 31)|2|/dataroot/@xsi/REGIONE/AS/OPERAZIONE/CONTRATTO/@giorno|
|Durata del contratto|8| |Durata del contratto espressa in mesi. Qualora il contratto di acquisizione preveda la consegna dei beni a fronte di un unico ordine (es. acquisto attrezzatura) si indicherà il valore “0” negli altri casi (es. noleggio di attrezzatura, contratto di somministrazione) la durata sarà quella prevista dal contratto. In nessun caso, per i contratti stipulati dopo il 2015 con dispositivi che abbiano una CND presente nel documento “Allegato 1 – Elenco CND per merceologie DPCM 24 dicembre 2015, la durata potrà essere superiore al 120 mesi.|N|OBB|Valore numerico intero compreso fra 0 e 120|3|/dataroot/@xsi/REGIONE/AS/OPERAZIONE/CONTRATTO/@durata_contr|
|Forma di negoziazione|9| |Forma di negoziazione con la quale è stato effettuato l’approvvigionamento dei dispositivi. Nel caso l’ambito di valenza sia Nazionale (es. Consip) è consentito valorizzare la forma a “NC”. Per i contratti stipulati dopo il 2015, con dispositivi che abbiano una CND presente nel documento “Allegato 1 – Elenco CND per merceologie DPCM 24 dicembre 2015, non sarà possibile acquisire il valore “NC” nel caso in cui l’ambito di valenza è “Aziendale”|AN|FAC|Valori di riferimento riportati nell’Allegato C-2|2|/dataroot/@xsi/REGIONE/AS/OPERAZIONE/CONTRATTO/@forma_neg|
|Ambito di valenza del contratto|10| |Ambito di valenza del contratto. In caso di adesione a convenzioni Consip la valenza sarà Nazionale; in tutti gli altri casi dovrà essere indicato il relativo ambito di valenza (regionale, sovraziendale o consortile, aziendale).|N|FAC|Valori di riferimento riportati nell’Allegato D|2|/dataroot/@xsi/REGIONE/AS/OPERAZIONE/CONTRATTO/@amb_val|
|Codice CIG|11| |Il codice CIG (codice identificativo di gara) è un codice alfanumerico generato dal sistema SIMOG della ANAC al fine di garantire:  • obblighi di comunicazione delle informazioni all’Osservatorio dei Contratti Pubblici per consentire l’identificazione univoca delle gare, dei loro lotti e dei contratti;  • collegamento al sistema di contribuzione posto a carico dei soggetti pubblici e privati sottoposti alla vigilanza dell’Autorità;  • (legge n. 136/2010 ) individuazione univoca delle movimentazioni finanziarie degli affidamenti di lavori, servizi o forniture, indipendentemente dalla procedura di scelta del contraente adottata, e dall’importo dell’affidamento stesso.   I CIG iniziano sempre con un numero mentre gli smartCIG iniziano sempre con una lettera (“X”, “Y” o”Z”). Nel caso in cui il dispositivo abbia una CND presente nel documento “Allegato 1 – Elenco CND per merceologie DPCM 24 dicembre 2015” e la tipologia di contratto sia stata valorizzata e non sia “Contratto di comodato” o “Contratto di donazione” il campo è obbligatorio. Nel caso in cui il CIG sia stato valorizzato deve essere formalmente corretto secondo i seguenti algoritmi: Il CIG sia NNNNNNNKKK la struttura del codice dove N è espresso in notazione decimale compresi eventuali 0 nelle posizioni più significative N&lt;&gt;’0000000’ e KKK= Hex [N* 211 mod 4091] dove Hex è la funzione di conversione da decimale a esadecimale . Lo smartCIG sia XKKCCCCCCC o YKKCCCCCCC o ZKKCCCCCCC la struttura del codice dove C è espresso in notazione esadecimale compresi eventuali 0 nelle posizioni più significative C&lt;&gt;’0000000’ e KK = Hex[Dec(C)*211 mod 251]|AN|NBB|Campo alfanumerico.|10|/dataroot/@xsi/REGIONE/AS/OPERAZIONE/CONTRATTO/@cod_cig|
|Tipo dispositivo medico|12|KEY|Codice che indica la tipologia di dispositivo medico oggetto di rilevazione (dispositivo di classe o assemblato)| |OBB|Valori di riferimento riportati nell’Allegato E|1|/dataroot/@xsi/REGIONE/AS/OPERAZIONE/CONTRATTO/DISPOSITIVO/@tipo_dispositivo|
|Identificativo di iscrizione in Banca Dati/Repertorio|13|KEY|Identificativo di iscrizione in Banca Dati/Repertorio ( BD/RDM) del dispositivo medico|N|OBB|Campo numerico|13|/dataroot/@xsi/REGIONE/AS/OPERAZIONE/CONTRATTO/DISPOSITIVO/@num_rep|
|Numero di pezzi presenti nella confezione minima di vendita|14| |Quantitativo Presente nella Confezione Distributiva. Per i soli dispositivi classificati come “sfusi” il campo deve contenere la quantità di dispositivo contenuta nella confezione minima, espressa nell’unità di misura chimico-fisica indicata per quel tipo di dispositivo nel documento apposito. Per i dispositivi non classificati come “sfusi” dovrà essere indicato il valore “0”.|N|FAC|Valore numerico compreso fra 0 e 999999.9999|11|/dataroot/@xsi/REGIONE/AS/OPERAZIONE/CONTRATTO/DISPOSITIVO/@num_pz|
|Denominazione del fornitore|15| |Denominazione del fornitore. Da indicare solo se il fornitore è diverso dal fabbricante|AN|FAC|Campo alfanumerico. Il carattere @ è riservato dunque non utilizzabile.|100|/dataroot/@xsi/REGIONE/AS/OPERAZIONE/CONTRATTO/DISPOSITIVO/@den_forn|
|Partita IVA del fornitore|16| |Partita IVA del fornitore italiano (o Vat Number del fornitore estero). Da indicare solo se il fornitore è diverso dal fabbricante|AN|FAC|Campo alfanumerico. Il carattere @ è riservato dunque non utilizzabile.|15|/dataroot/@xsi/REGIONE/AS/OPERAZIONE/CONTRATTO/DISPOSITIVO/@piva_forn|
|Quantità aggiudicata|17| |Si tratta della quantità indicata esplicitamente nel bando di gara o comunicata esplicitamente ai fornitori per la formulazione dell’offerta. Sarà consentito inserire il valore ‘0’ solo in presenza di adesioni a convenzioni Consip, o altra Centrale di Committenza, qualora non sia nota la “quantità aggiudicata” (negoziata) o in caso di contratto a listino/budget. Per i contratti stipulati dopo il 2015, con dispositivi che abbiano una CND presente nel documento “Allegato 1 – Elenco CND per merceologie DPCM 24 dicembre 2015, non sarà possibile acquisire il valore “0” nel caso in cui l’ambito di valenza è “Aziendale” e la tipologia di contratto è diversa da “Contratto di acquisto da listino / budget”. Per i contratti stipulati dopo il 2015, con dispositivi che abbiano una CND presente nel documento “Allegato 1 – Elenco CND per merceologie DPCM 24 dicembre 2015,non potrà essere inviato un importo (quantità aggiudicata * prezzo unitario ) superiore ai 40.000€ nel caso di “Affidamento diretto”.|N|OBB|Valore numerico compreso fra 0 e 999999999999.99|15|/dataroot/@xsi/REGIONE/AS/OPERAZIONE/CONTRATTO/DISPOSITIVO/@qta_agg|
|Quantità contrattualizzata|18| |Si tratta dei quantitativi di adesione della singola Azienda Sanitaria. Nel caso di dispositivi non “sfusi si tratta del numero di pezzi del dispositivo previsto dal contratto stipulato con il fornitore. Nel caso di dispositivi “sfusi” la quantità corrisponde al numero di confezioni che contengono la quantità specificata nel campo “Numero di pezzi presenti nella confezione minima di vendita”. Qualora si sia in presenza di contratti che non permettono il perfezionamento dell’accordo senza che venga definita la quantità (Somministrazioni/A Listino/A Budget), le regole precedenti non si applicano e dovrà essere indicato il valore “0”. Per i contratti stipulati dopo il 2015, con dispositivi che abbiano una CND presente nel documento “Allegato 1 – Elenco CND per merceologie DPCM 24 dicembre 2015,non sarà possibile acquisire il valore “0” nel caso in cui l’ambito di valenza è “Aziendale” e la tipologia di contratto è diversa da “Contratto di acquisto da listino / budget”. In nessun caso, per i contratti stipulati dopo il 2015, la quantità contrattualizzata potrà essere superiore a quella aggiudicata.|N|OBB|Valore numerico compreso fra 0 e 999999999999.99|15|/dataroot/@xsi/REGIONE/AS/OPERAZIONE/CONTRATTO/DISPOSITIVO/@qta_contr|
|Prezzo unitario aggiudicato|19| |Si tratta del Prezzo Unitario Contrattualizzato ( IVA esclusa). Se la tipologia di contratto è “CA” il prezzo unitario corrisponde al costo di acquisto di 1 pezzo o di una confezione (solo per gli “sfusi”) di quantitativo pari a quanto indicato nel campo “Numero di pezzi presenti nella confezione minima di vendita”; se la tipologia di contratto è “CC o “CO” il prezzo unitario corrisponde al costo di acquisizione di un dispositivo per un singolo mese; se la tipologia di contratto è “LF” il prezzo unitario corrisponde al valore della rata mensile per un singolo dispositivo aumentata del valore di riscatto ripartito equamente sul numero totale di rate previste dal contratto per quel singolo dispositivo. Si noti che gli acquisti finanziati non hanno alcun legame giuridico con i contratti di leasing, di locazione, etc.; i primi andranno trattati al pari di qualunque acquisto. In caso di Donazioni e Comodati Gratuiti valorizzare a”0” (zero). Per i contratti stipulati dopo il 2015, con dispositivi che abbiano una CND presente nel documento “Allegato 1 – Elenco CND per merceologie DPCM 24 dicembre 2015, non sarà possibile acquisire il valore “0” per tutti i dispositivi medici dello stesso contratto nel caso in cui l’ambito di valenza è “Aziendale” e la tipologia di contratto è “Contratto di acquisto”. Per i contratti stipulati dopo il 2015, con dispositivi che abbiano una CND presente nel documento “Allegato 1 – Elenco CND per merceologie DPCM 24 dicembre 2015, non potrà essere inviato un importo (quantità aggiudicata * prezzo unitario ) superiore ai 40.000€ nel caso di “Affidamento diretto”.|N|OBB|Valore numerico compreso tra 0 e 99999999.99999|14|/dataroot/@xsi/REGIONE/AS/OPERAZIONE/CONTRATTO/DISPOSITIVO/@prezzo_agg|
|Aliquota IVA|20| |Tipologia di aliquota prevista nel contratto: indicare se si tratta di aliquota standard, agevolata o mista. La tipologia “mista” è prevista nei casi in cui all’interno di uno stesso contratto, per uno stesso dispositivo medico sono applicate aliquote IVA diverse.|A|OBB|Campo alfanumerico. Valori ammessi: - S (Aliquota Standard) - A (Aliquota Agevolata) - M (Aliquota mista)|1|/dataroot/@xsi/REGIONE/AS/OPERAZIONE/CONTRATTO/DISPOSITIVO/@iva|
|Servizi accessori|21| |Indica la presenza di “Servizi Accessori” che possono influire sul prezzo di acquisto, quali ad esempio:  - adeguamento tecnologico;  - assistenza specialistica al personale (es. formazione);  - trasporto (al domicilio, nella sede di utilizzo);  - forme di confezionamento che possono favorire l’organizzazione locale;  - altro|AN|FAC|Campo alfanumerico. Valori ammessi: - SI - NO|2|/dataroot/@xsi/REGIONE/AS/OPERAZIONE/CONTRATTO/DISPOSITIVO/@flg_ser_acc|
|Conto deposito|22| |Indica se il servizio di conto deposito è compreso o meno nel prezzo di fornitura del dispositivo medico|AN|OBB|Campo alfanumerico. Valori ammessi: - SI - NO|2|/dataroot/@xsi/REGIONE/AS/OPERAZIONE/CONTRATTO/DISPOSITIVO/@flg_conto_dep|
|Voce di imputazione nel modello C.E.|23| |Codice che identifica la voce di Conto Economico nella quale viene imputato il costo del dispositivo medico oggetto del contratto|AN|FAC| |6|/dataroot/@xsi/REGIONE/AS/OPERAZIONE/CONTRATTO/DISPOSITIVO/@cod_mod_ce|
|Progressivo riga|24|KEY|Progressivo che permette di imputare dati diversi (es. prezzo, quantità, ecc.) ad uno stesso numero di repertorio nel caso in cui il dispositivo faccia riferimento ad una “notifica multipla”. Tale progressivo, se utilizzato, costituisce parte della chiave univoca dei dati del tracciato|N|FAC|Valore numerico compreso fra 1 e 99999|5|/dataroot/@xsi/REGIONE/AS/OPERAZIONE/CONTRATTO/DISPOSITIVO/@progr_riga|
|Tipo operazione|25| |Campo tecnico utilizzato per distinguere la trasmissione di informazioni nuove, modificate o eventualmente annullate.  Va utilizzato il codice “I” per la trasmissione di informazioni nuove o per la ritrasmissione di informazioni precedentemente scartate dal sistema di acquisizione.  Va utilizzato il codice “V” per la trasmissione di informazioni per le quali si intende fare effettuare una sovrascrittura dal sistema di acquisizione.  Va utilizzato il codice “C” per la trasmissione di informazioni per le quali si intende fare effettuare una cancellazione dal sistema di acquisizione.|A|OBB|Valori ammessi: - I: Inserimento - V: Variazione - C: Cancellazione|1|/dataroot/@xsi/REGIONE/AS/OPERAZIONE/@tipo_op|


## ***3.3 Controlli di validazione del dato (business rules)***

Di seguito sono indicati i controlli da configurare sulla componente di Validation Engine e rispettivi error code associati riscontrabili sui dati di input per il flusso **CT2**.

Gli errori sono solo di tipo scarti (mancato invio del record).

Al verificarsi anche di un solo errore di scarto, tra quelli descritti, il record oggetto di controllo sarà inserito tra i record scartati.

Business Rule non implementabili lato SDK:

- Storiche (Business Rule che effettuano controlli su dati già acquisiti/consolidati che non facciano parte del dato anagrafico)
- Transazionali (Business Rule che effettuano controlli su record, i quali rappresentano transazioni, su cui andrebbe garantito l’ACID (Atomicità-Consistenza-Isolamento-Durabilità))
- Controllo d’integrità (cross flusso) (Business Rule che effettuano controlli sui record utilizzando informazioni estratte da record di altri flussi)

Di seguito le BR per il flusso in oggetto:

| | | | | | | | | | |
|-|-|-|-|-|-|-|-|-|-|
|**CAMPO**|**CODICE ERRORE**|**DESCRIZIONE ERRORE**|**DESCRIZIONE MDS**|**DESCRIZIONE ALGORITMO**|**TABELLA ANAGRAFICA**|**CAMPI DI COERENZA**|**SCARTI/ANOMALIE**|**TIPOLOGIA BR**|**TIPOLOGIA CONTROLLO BR**|
|cod_as|XSD_1|Coerenza dominio valori|Espressione regolare: [0-9]{6}|Non Definito|Non Definito|Non Definito|Scarti|Basic|SINTATTICI|
|num_contr|XSD_2|Coerenza dominio valori|Valori ammessi: stringa da 1 a 40 caratteri escluso @|Non Definito|Non Definito|Non Definito|Scarti|Basic|SINTATTICI|
|tipo_contr|XSD_3|Coerenza dominio valori|Valori ammessi (se campo non vuoto): CA, CB, CC, CD, CO, CS, LF|Non Definito|Non Definito|Non Definito|Scarti|Basic|SINTATTICI|
|anno|XSD_4|Coerenza dominio valori|Espressione regolare: [2][0][0-9]{2}|Non Definito|Non Definito|Non Definito|Scarti|Basic|SINTATTICI|
|mese|XSD_5|Coerenza dominio valori|Espressione regolare: 0[1-9]"|1[012]|Non Definito|Non Definito|Non Definito|Scarti|Basic|SINTATTICI|
|giorno|XSD_6|Coerenza dominio valori|Espressione regolare: 0[1-9]"|[12][0-9]"|3[01]|Non Definito|Non Definito|Non Definito|Scarti|Basic|SINTATTICI|
|durata_contr|XSD_7|Coerenza dominio valori|Valori ammessi: da 0 a 999|Non Definito|Non Definito|Non Definito|Scarti|Basic|SINTATTICI|
|forma_neg|XSD_8|Coerenza dominio valori|Valori ammessi (se campo non vuoto):  PA, PR, PS, PP, CF, AD, NC|Non Definito|Non Definito|Non Definito|Scarti|Basic|SINTATTICI|
|amb_val|XSD_9|Coerenza dominio valori|Valori ammessi (se campo non vuoto): 1,2,3,4|Non Definito|Non Definito|Non Definito|Scarti|Basic|SINTATTICI|
|cod_cig|XSD_10|Coerenza dominio valori|Valori ammessi (se campo non vuoto): stringa da 1 a 10 caratteri escluso @|Non Definito|Non Definito|Non Definito|Scarti|Basic|SINTATTICI|
|tipo_dispositivo|XSD_11|Coerenza dominio valori|Valori ammessi: 1 oppure 2|Non Definito|Non Definito|Non Definito|Scarti|Basic|SINTATTICI|
|num_rep|XSD_12|Coerenza dominio valori|Valori ammessi: intero da 1 a 9999999999999|Non Definito|Non Definito|Non Definito|Scarti|Basic|SINTATTICI|
|num_pz|XSD_13|Coerenza dominio valori|Valori ammessi (se campo non vuoto): decimale da 0.0000 a 999999.9999 pattern: \d{1,6}([.]\d{1,4})?|Non Definito|Non Definito|Non Definito|Scarti|Basic|SINTATTICI|
|den_forn|XSD_14|Coerenza dominio valori|Valori ammessi (se campo non vuoto): stringa da 1 a 100 caratteri escluso @|Non Definito|Non Definito|Non Definito|Scarti|Basic|SINTATTICI|
|piva_forn|XSD_15|Coerenza dominio valori|Valori ammessi (se campo non vuoto): stringa da 1 a 15 caratteri escluso @|Non Definito|Non Definito|Non Definito|Scarti|Basic|SINTATTICI|
|qta_agg|XSD_16|Coerenza dominio valori|Valori ammessi: decimale da 0.00 a 999999999999.99 pattern: \d{1,12}([.]\d{1,2})?|Non Definito|Non Definito|Non Definito|Scarti|Basic|SINTATTICI|
|qta_contr|XSD_17|Coerenza dominio valori|Valori ammessi: decimale da 0.00 a 999999999999.99 pattern: \d{1,12}([.]\d{1,2})?|Non Definito|Non Definito|Non Definito|Scarti|Basic|SINTATTICI|
|prezzo_agg|XSD_18|Coerenza dominio valori|Valori ammessi: decimale da 0.00000 a 99999999.99999 pattern: \d{1,8}([.]\d{1,5})?|Non Definito|Non Definito|Non Definito|Scarti|Basic|SINTATTICI|
|iva|XSD_19|Coerenza dominio valori|Valori ammessi: S, A, M|Non Definito|Non Definito|Non Definito|Scarti|Basic|SINTATTICI|
|flg_ser_acc|XSD_20|Coerenza dominio valori|Valori ammessi (se campo non vuoto): SI, NO|Non Definito|Non Definito|Non Definito|Scarti|Basic|SINTATTICI|
|flg_conto_dep|XSD_21|Coerenza dominio valori|Valori ammessi: SI, NO|Non Definito|Non Definito|Non Definito|Scarti|Basic|SINTATTICI|
|cod_mod_ce|XSD_22|Coerenza dominio valori|Valori ammessi (se campo non vuoto): stringa da 1 a 6 caratteri|Non Definito|Non Definito|Non Definito|Scarti|Basic|SINTATTICI|
|progr_riga|XSD_23|Coerenza dominio valori|Valori ammessi (se campo non vuoto): intero da 1 a 99999|Non Definito|Non Definito|Non Definito|Scarti|Basic|SINTATTICI|
|tipo_op|XSD_24|Coerenza dominio valori|Valori ammessi: I, V, C|Non Definito|Non Definito|Non Definito|Scarti|Basic|SINTATTICI|
|Codice Regione|B01|Errore codice regione|Il parametro Codice Regione passato in input all'SDK non coincide con il campo del tracciato|Il parametro Codice Regione passato in input all'SDK deve coincidere con il campo cod_reg del tracciato di input. Inoltre il codice deve essere presente in anagrafica.|Il parametro Codice Regione passato in input all'SDK deve coincidere con il campo cod_reg del tracciato di input. Inoltre il valore del campo cod_reg del tracciato di input deve essere presente all'interno della query Anagrafica Regioni.   Le anagrafiche devono essere filtrate sulla condizione:   il periodo (campi "anno" e "mese") di riferimento del tracciato di input deve essere compreso tra le date di validità riportate nelle colonne DAT_INI_VLD, DAT_END_VLD_EFT della query.  |Regioni|Scarti|Anagrafica|SINTATTICI|
|Anno di stipula contratto; Mese di stipula contratto; Giorno di stipula contratto;|B03|Errore periodo di riferimento|La data di invio del file deve essere successiva al periodo di riferimento. Questo errore viene restituito quando il periodo a cui si riferiscono i dati all’interno del file è successivo alla data di invio del file.|La data di esecuzione dell'SDK deve essere maggiore rispetto alla data del periodo (anno-mese-giorno) di riferimento nel tracciato di input.|Non Definito|Non Definito|Scarti|Basic|SINTATTICI|
|Codice Regione; Codice Azienda Sanitaria; Codice Contratto; Tipo dispositivo medico; Codice dispositivo medico|B95|Errore formato dati comunicati|Per il “Codice Regione”, “Codice Azienda Sanitaria”, “Codice Contratto”, “tipo dispositivo medico” e “Codice Dispositivo Medico” non possono essere comunicati contemporaneamente dati aggregati e dati di dettaglio. Questo errore viene restituito quando all’interno dello stesso file sono presenti per lo stesso “Codice Regione”, “Codice Azienda Sanitaria”, “Codice Contratto”, “tipo dispositivo medico” e “Codice Dispositivo Medico” record con diverso livello di aggregazione, ovverosia sono presenti , per la stessa suddetta chiave di aggregazione, sia record senza l’indicazione del “Progressivo Riga”, sia record con l’indicazione del “Progressivo Riga”.|Bisogna controllare che all'interno di un file non esistano più record che, a parità di valori nei campi indicati nella prima colonna, non abbiano livelli di aggregazione diversi, ovvero campi facoltativi impostati a null su un record e valorizzati su un altro.  NB: occorre utilizzare un DB di appoggio|Non Definito|Non Definito|Scarti|Basic|SINTATTICI|
|Durata del contratto|B98|Errore Durata del contratto|Per i contratti stipulati dal 2016 , con dispositivi che abbiano una CND presente nel documento “Allegato 1 – Elenco CND per merceologie DPCM 24 dicembre 2015, la “Durata del contratto” non può avere un valore maggiore di 120. Questo errore viene restituito quando viene inviato un valore maggiore di 120 nel campo “Durata del contratto” e il contratto è stipulato dopo il 2015, il dispositivo appartiene ad una CND presente nell’Allegato 1 – Elenco CND per merceologie DPCM 24 dicembre 2015|Se anno &gt;= 2016 e se CND presente in allegato (vedi BR - Definizione (2)) allora durata_contr deve essere minore o uguale a 120|CND_Allegato (per controllo che CND sia appartenente a Elenco CND per merceologie DPCM 24 dicembre 2015 |Anno di stipula contratto; Tipo Dispositivo; Identificativo di iscrizione in Banca Dati/ Repertorio;|Scarti|Basic + Anagrafica|SINTATTICI|
|Forma di negoziazione|B99|Errore Forma di negoziazione|Per i contratti stipulati dal 2016, con dispositivi che abbiano una CND presente nel documento “Allegato 1 – Elenco CND per merceologie DPCM 24 dicembre 2015, la Forma di negoziazione deve essere valorizzata. Questo errore viene restituito quando viene inviato il valore “NC” nel campo Forma di negoziazione e l’Ambito di Valenza è “Aziendale” e il contratto è stipulato dopo il 2015 e il dispositivo appartiene ad una CND presente nell’Allegato 1 – Elenco CND per merceologie DPCM 24 dicembre 2015|Se anno &gt;= 2016 AND  CND presente in allegato (vedi BR - Definizione (2)) AND  amb_val è uguale a 4 (aziendale)  ALLORA forma_neg deve essere diversa da "NC"|CND_Allegato (per controllo che CND sia appartenente a Elenco CND per merceologie DPCM 24 dicembre 2016|Anno di stipula contratto;  Ambito Valenza Contratto;  Forma di negoziazione; Tipo Dispositivo; Identificativo di iscrizione in Banca Dati/ Repertorio;|Scarti|Basic + Anagrafica|SINTATTICI|
|Quantità aggiudicata|B100|Errore Quantità aggiudicata|Per i contratti stipulati dal 2016,con dispositivi che abbiano una CND presente nel documento “Allegato 1 – Elenco CND per merceologie DPCM 24 dicembre 2015, la Quantità aggiudicata deve essere valorizzata. Questo errore viene restituito quando viene inviato il valore 0 nel campo Quantità aggiudicata,l’Ambito di Valenza è “Aziendale” e la tipologia di contratto è diversa da “Contratto di acquisto da listino / budget” e il contratto è stipulato dopo il 2015, e il dispositivo appartiene ad una CND presente nell’Allegato 1 – Elenco CND per merceologie DPCM 24 dicembre 2015|Se anno &gt;= 2016 AND   CND presente in allegato (vedi BR - Definizione (2)) AND  amb_val è uguale a 4 (aziendale)  AND tipo_contr diverso da "CB" ALLORA qta_agg deve essere diversa da 0|CND_Allegato (per controllo che CND sia appartenente a Elenco CND per merceologie DPCM 24 dicembre 2017|Anno di stipula contratto;  Ambito valenza; Tipo contratto; Tipo Dispositivo; Identificativo di iscrizione in Banca Dati/ Repertorio;|Scarti|Basic + Anagrafica|SINTATTICI|
|Quantià contrattualizzata|B101|Errore Quantità contrattualizzata|Per i contratti stipulati dal 2016, con dispositivi che abbiano una CND presente nel documento “Allegato 1 – Elenco CND per merceologie DPCM 24 dicembre 2015, la Quantità contrattualizzata deve essere valorizzata. Questo errore viene restituito quando viene inviato il valore 0 nel campo Quantità contrattualizzata, l’Ambito di Valenza è “Aziendale” e la tipologia di contratto è diversa da “Contratto di acquisto da listino / budget” e il contratto è stipulato dopo il 2015, e il dispositivo appartiene ad una CND presente nell’Allegato 1 – Elenco CND per merceologie DPCM 24 dicembre 2015|Se anno &gt;= 2016 AND  CND presente in allegato (vedi BR - Definizione (2)) AND  amb_val è uguale a 4 (aziendale)  AND tipo_contr diverso da "CB" ALLORA qta_contr deve essere diversa da 0|CND_Allegato (per controllo che CND sia appartenente a Elenco CND per merceologie DPCM 24 dicembre 2018|Anno di stipula contratto;  Ambito valenza; Tipo contratto; Tipo Dispositivo; Identificativo di iscrizione in Banca Dati/ Repertorio;|Scarti|Basic + Anagrafica|SINTATTICI|
|Quantità aggiudicata; Quantità contrattualizzata|B102|Errore nelle quantità|Per i contratti stipulati dal 2016, con dispositivi che abbiano una CND presente nel documento “Allegato 1 – Elenco CND per merceologie DPCM 24 dicembre 2015, la Quantità aggiudicata deve essere congruente con la Quantità contrattualizzata. Questo errore viene restituito quando viene inviata una Quantità contrattualizzata superiore alla Quantità aggiudicata e il contratto è stipulato dopo il 2015, e il dispositivo appartiene ad una CND presente nell’Allegato 1 – Elenco CND per merceologie DPCM 24 dicembre 2015|Se anno &gt;= 2016 AND   CND presente in allegato (vedi BR - Definizione (2))  ALLORA qta_agg deve essere maggiore o uguale a qta_contr|CND_Allegato (per controllo che CND sia appartenente a Elenco CND per merceologie DPCM 24 dicembre 2019|Anno di stipula contratto;  Tipo Dispositivo; Identificativo di iscrizione in Banca Dati/ Repertorio;|Scarti|Basic + Anagrafica|SINTATTICI|
|Prezzo unitario aggiudicato|B103|Errore Prezzo unitario|Per i contratti stipulati dal 2016, con dispositivi che abbiano una CND presente nel documento “Allegato 1 – Elenco CND per merceologie DPCM 24 dicembre 2015, il Prezzo unitario deve essere valorizzato. Questo errore viene restituito quando viene inviato il valore 0 nel campo Prezzo unitario per tutti i dispositivi dello stesso contratto, l’Ambito di Valenza è “Aziendale” e la tipologia di contratto è “Contratto di acquisto” e il contratto è stipulato dopo il 2015, e il dispositivo appartiene ad una CND presente nell’Allegato 1 – Elenco CND per merceologie DPCM 24 dicembre 2015|Se anno &gt;= 2016 AND   CND presente in allegato (vedi BR - Definizione (2))  AND  tipo_contr uguale a "CA" amb_val è uguale a 4 (aziendale)  ALLORA prezzo_agg deve essere diverso da 0|CND_Allegato (per controllo che CND sia appartenente a Elenco CND per merceologie DPCM 24 dicembre 2020|Anno di stipula contratto;  Ambito di Valenza; Tipologia Contratto; Tipo Dispositivo; Identificativo di iscrizione in Banca Dati/ Repertorio;|Scarti|Basic + Anagrafica|SINTATTICI|
|Quantità aggiudicata; Prezzo unitario aggiudicato|B104|Errore importo|Per i contratti stipulati dal 2016, con dispositivi che abbiano una CND presente nel documento “Allegato 1 – Elenco CND per merceologie DPCM 24 dicembre 2015, l’importo non può superare la soglia. Questo errore viene restituito quando viene inviato un importo (quantità aggiudicata * prezzo unitario ) superiore ai 40.000 € e la Forma di negoziazione è “Affidamento diretto” e il contratto è stipulato dopo il 2015, e il dispositivo appartiene ad una CND presente nell’Allegato 1 – Elenco CND per merceologie DPCM 24 dicembre 2015|Se anno &gt;= 2016 AND  CND presente in allegato (vedi BR - Definizione (2))  AND  forma_neg uguale a "AD" ALLORA qta_agg *prezzo_agg deve essere minore o uguale a 40.000|CND_Allegato (per controllo che CND sia appartenente a Elenco CND per merceologie DPCM 24 dicembre 2021|Anno di stipula contratto;  Forma di negoziazione; Tipo Dispositivo; Identificativo di iscrizione in Banca Dati/ Repertorio;|Scarti|Basic + Anagrafica|SINTATTICI|
|Codice CIG|B105|Errore Codice CIG|Codice CIG obbligatorio per dispositivi appartenenti ad “Allegato 1 – Elenco CND per merceologie DPCM 24 dicembre 2015”. Questo errore viene restituito quando non viene inviato il codice CIG, il dispositivo appartiene ad una CND presente nell’Allegato 1 – Elenco CND per merceologie DPCM 24 dicembre 2015 e la tipologia di contratto deve essere valorizzata ed è diversa da “Contratto di comodato” e da “Contratto di donazione”.|Se tipo_contr è valorizzato AND tipo_contr diverso da "CC" e da "CD" AND CND presente in allegato (vedi BR - Definizione (2)) ALLORA cod_cig deve essere valorizzato|CND_Allegato (per controllo che CND sia appartenente a Elenco CND per merceologie DPCM 24 dicembre 2022|Tipologia di Contratto; Tipo Dispositivo; Identificativo di iscrizione in Banca Dati/ Repertorio;|Scarti|Basic + Anagrafica|OBBLIGATORIETA' DEL DATO|
|Codice CIG|B106|Errore Codice CIG|Codice CIG non formalmente corretto. Questo errore viene restituito quando viene inviato un codice CIG non formalmente corretto|Il CIG deve rispettare una delle seguenti notazioni con relative regole.  CIG: sia NNNNNNNKKK la struttura del codice dove N è espresso in notazione decimale compresi eventuali 0 nelle posizioni più significative. N&lt;&gt;’0000000’ e KKK = Hex [N * 211 mod 4091]  smartCIG: sia XKKCCCCCCC o YKKCCCCCCC o ZKKCCCCCCC la struttura del codice dove C è espresso in notazione esadecimale compresi eventuali 0 nelle posizioni più significative. C&lt;&gt;’0000000’ e KK = Hex[Dec(C)*211 mod 251] e X, Y o K sono caratteri fissi (la stringa deve iniziare con uno dei tre)  NB: Hex è la funzione di conversione da decimale a esadecimale|Non Definito|Non Definito|Scarti|Basic|SINTATTICI|
|Codice Azienda Sanitaria|D01|Errore di dominio codice AS|Il codice azienda non esiste per la regione inviata. Questo errore viene restituito quando, il codice AS inviato non è presente in anagrafica.|la coppia  (cod_reg, cod_asl) del tracciato di input deve essere presente all'interno dell'anagrafica ASL.  Le anagrafiche devono essere filtrate sulla condizione:   il periodo (campi "anno" e "mese") di riferimento del tracciato di input deve essere compreso tra le date di validità riportate nelle colonne DAT_INI_VLD, DAT_END_VLD_EFT della query. |ASL|Non Definito|Scarti|Anagrafica|SINTATTICI|
|Identificativo di iscrizione in Banca Dati/ Repertorio|D81|Errore numero repertorio Dispositivo di Classe|Il numero di repertorio inviato non corrisponde a nessun Dispositivo Medico di Classe presente nella Banca Dati dei Dispositivi Medici. Questo errore viene restituito quando, il numero di repertorio del dispositivo medico di classe inviato non è presente in anagrafica.|Se tipo_dispositivo='1' allora il campo num_rep deve essere presente in anagrafica.|Dispositivi|Tipo Dispositivo|Scarti|Anagrafica|SINTATTICI|
|Identificativo di iscrizione in Banca Dati/ Repertorio|D83|Errore numero repertorio Assemblato|Il numero di repertorio inviato non corrisponde a nessun Assemblato presente nella Banca Dati dei Dispositivi Medici. Questo errore viene restituito quando, il numero di repertorio dell’assemblato inviato non è presente in anagrafica.|Se tipo_dispositivo='2' allora il campo num_rep deve essere presente in anagrafica.|Assemblati|Tipo Dispositivo|Scarti|Anagrafica|SINTATTICI|
|Anno di stipula contratto; Mese di stipula contratto; Giorno di stipula contratto;|D88|Errore data stipula contratto|I valori anno,mese,giorno di stipula del contratto non formano una data valida. Questo errore viene restituito quando la data di stipula desunta dai tre campi Anno,Mese,Giorno stipula contratto non è una data valida|Verificare che anno+"-"+mese+"-"+giorno formino una data corretta nel formato (YYYY-MM-DD)|Non Definito|Non Definito|Scarti|Basic|SINTATTICI|


## ***3.4 Accesso alle anagrafiche***

I controlli applicativi saranno implementati a partire dall’acquisizione dei seguenti dati anagrafici disponibili in ambito MdS e recuperati con servizi ad hoc (Service Layer mediante PDI):

- Regioni 
- ASL
- Strutture utilizzatrici
- Codici Unità Operative
- Dispositivi
- Assemblati
- Allegato 1 – Elenco CND per merceologie DPCM 24 dicembre 2015

All’interno del file **censimento\_anagrafiche** sono presenti per ogni anagrafica il dettaglio implementativo (Query SQL) e la tabella fisica da cui alimentare l’anagrafica.

Il dato anagrafico sarà presente sottoforma di tabella composta da tre colonne:

- Valore (in cui è riportato il dato, nel caso di più valori, sarà usato il carattere # come separatore)

- Data inizio validità (rappresenta la data di inizio validità del campo Valore)
 - Formato: AAAA-MM-DD
 - Notazione inizio validità permanente: **1900-01-01**


- Data Fine Validità (rappresenta la data di fine validità del campo Valore)
  - Formato: AAAA-MM-DD
  - Notazione fine validità permanente: **9999-12-31**

Affinchè le Business Rule che usano il dato anagrafico per effettuare controlli siano correttamente funzionanti, occorre controllare che la data di competenza del record su cui si effettua il controllo (la quale varia in base alla componente) sia compresa tra le data di validità (ove presenti).

Di seguito viene mostrato un caso limite di anagrafica in cui sono presenti delle sovrapposizioni temporali e contraddizioni di validità permanente/specifico range.


|ID|VALUE|VALID\_FROM|VALID\_TO|
| - | - | - | - |
|1|VALORE 1|1900-01-01|9999-12-31|
|2|VALORE 1|2015-01-01|2015-12-31|
|3|VALORE 1|2018-01-01|2023-12-31|
|4|VALORE 1|2022-01-01|2024-12-31|


Diremo che :  il dato presente sul tracciato di input è valido se e solo se:

∃ VALUE\_R = VALUE\_A “tale che” VALID\_FROM<= **DATA\_COMPETENZA** <= VALID\_TO

(Esiste almeno un valore compreso tra le date di validità)

Dove:

- ` `VALUE\_R rappresenta i campi del tracciato di input coinvolti nei controlli della specifica BR

- VALUE\_A rappresenta i campi dell’anagrafica coinvolti nei controlli della specifica BR

- VALID\_FROM/VALID\_TO rappresentano le colonne dell’anagrafica

- DATA\_COMPETENZA rappresenta un campo sul tracciato di input con cui controllare il dato anagrafico (composto da anno e mese di riferimento).


## ***3.5 Alimentazione MdS***

### **3.5.1 Invio Flussi**

A valle delle verifiche effettuate dal Validation Engine, qualora il caricamento sia stato effettuato con il parametro Tipo Elaborazione impostato a P, verranno inviati verso MdS tutti i record corretti secondo le regole di validazione impostate.

Verrà richiamata la procedura invioFlussi di GAF WS (tramite PDI) alla quale verranno passati in input i parametri così come riportati nella seguente tabella:

|**NOME PARAMETRO**|**VALORIZZAZIONE**|
| :- | :- |
|ANNO RIFERIMENTO|Parametro ANNO RIFERIMENTO in input a SDK|
|PERIODO RIFERIMENTO|Parametro PERIODO RIFERIMENTO in input a SDK |
|CATEGORIA FLUSSI|CDM|
|NOME FLUSSO|CT2|
|NOME FILE|Parametro popolato dall’SDK in fase di invio flusso con il nome file generato dal Validation Engine in fase di produzione file.|

### **3.5.2 Flussi di Output per alimentazione MdS**

Il flussi generati dall’SDK saranno presenti sotto la cartella /sdk/xml\_output e dovranno essere salvati e inviati verso MdS rispettando la seguente nomenclatura:

**SDK\_CDM\_CT2\_{Periodo di riferimento}\_{ID\_RUN}.xml**

Dove :

- ID\_RUN rappresenta l’identificativo univoco
- Periodo di riferimento rappresenta il periodo specificato in input al SDK

### **3.5.3 Gestione Risposta Mds**

A valle della presa in carico del dato da parte di MdS, SDK riceverà una response contenente le seguenti informazioni:

1. **codiceFornitura**: stringa numerica indicante l’identificativo univoco della fornitura inviata al GAF
1. **errorCode**: stringa alfanumerica di 256 caratteri rappresentante il codice identificativo dell’errore eventualmente riscontrato
1. **errorText**: stringa alfanumerica di 256 caratteri rappresentante il descrittivo dell’errore eventualmente riscontrato
1. Insieme delle seguenti triple, una per ogni file inviato:
1. **idUpload**: stringa numerica indicante l’identificativo univoco del singolo file ricevuto ed accettato dal MdS, e corrispondente al file inviato con la denominazione riportata nell’elemento “nomeFile” che segue
1. **esito**: stringa alfanumerica di 4 caratteri rappresentante l’esito dell’operazione (Vedi tabella sotto)
1. **nomeFile**: stringa alfanumerica di 256 caratteri rappresentante il nome dei file inviati.

Di seguito la tabella di riepilogo dei codici degli esiti possibili dell’invio del file


|**ESITO**|**DESCRIZIONE**|
| :- | :- |
|AE00|Errore di autenticazione al servizio|
|IF00|Operazione completata con successo|
|IF01|Incongruenza tra CF utente firmatario e cf utente inviante|
|IF02|Firma digitale non valida|
|IF03|Firma digitale scaduta|
|IF04|Estensione non ammessa|
|IF05|Utente non abilitato all’invio per la Categoria Flusso indicata|
|IF06|Utente non abilitato all’invio per il Flusso indicata|
|IF07|Periodo non congurente con la Categoria Flusso indicata|
|IF08|Il file inviato è vuoto|
|IF09|Errore interno al servizio nella ricezione del file|
|IF10|Il numero di allegati segnalati nel body non corrisponde al numero di allegati riscontrati nella request|
|IF11|Il nome dell’allegato riportato nel body non è presente tra gli allegati della request (content-id)|
|IF12|Presenza di nomi file duplicati|
|IF13|Errore interno al servizio nella ricezione del file|
|IF14|Errore interno al servizio nella ricezione del file|
|IF15|Errore interno al servizio nella ricezione del file|
|IF99|Errore generico dell’operation|

Copia dei file inviati verso MdS il cui esito è positivo (ovvero risposta della procedura Invio Flussi con IF00) saranno trasferiti e memorizzati in una cartella ad hoc di SDK (es. /sdk/sent) rispettando la stessa naming descritta per i flussi di output.


## ***3.6 Scarti di processamento***

In una cartella dedicata (es. /sdk/esiti) verrà creato un file json contenente il dettaglio degli scarti riscontrati ad ogni esecuzione del processo SDK.

Il naming del file sarà:  ESITO\_{ID\_RUN}.json

Dove:

- ID\_RUN rappresenta l’identificativo univoco dell’elaborazione

Di seguito il tracciato del record da produrre.


|**CAMPO**|**DESCRIZIONE**|
| :- | :- |
|NUMERO RECORD|Numero del record del flusso input|
|RECORD PROCESSATO|Campi esterni rispetto al tracciato, che sono necessari per la validazione dello stesso.|
||Record su cui si è verificato uno scarto, riportato in maniera strutturata (nome\_campo-valore).|
|LISTA ESITI|<p>Lista di oggetti contenente l’esito di validazione per ciascun campo:</p><p>- Campo: nome campo su cui si è verificato uno scarto</p><p>- Valore Scarto: valore del campo su cui si è verificato uno scarto</p><p>- Valore Esito: esito di validazione del particolare campo</p><p>- Errori Validazione: contiene i campi Codice (della Business Rule) e Descrizione (della Business Rule)</p>|

## ***3.7 Informazioni dell’esecuzione***

In una cartella dedicata (es. /sdk/run) verrà creato un file contenente il dettaglio degli esiti riscontrati ad ogni esecuzione del processo SDK. Verrà prodotto un file di log per ogni giorno di elaborazione.

Il naming del file sarà:  

{ID\_RUN}.json

Dove:

- ID\_RUN rappresenta l’identificativo univoco dell’elaborazione

Di seguito il tracciato del record da produrre.


|**CAMPO**|**DESCRIZIONE**|
| :- | :- |
|ID RUN (chiave)|Identificativo univoco di ogni esecuzione del SDK|
|ID\_CLIENT|Identificativo Univoco della trasazione sorgente che richiede processamento lato SDK|
|ID UPLOAD (chiave)|Identificativo di caricamento fornito da MdS|
|TIPO ELABORAZIONE|F (full)/R (per singolo record) - Impostato di default a F|
|MODALITA’ OPERATIVA|P (=produzione) /T (=test)|
|DATA INIZIO ESECUZIONE|Timestamp dell’ inizio del processamento|
|DATA FINE ESECUZIONE|Timestamp di completamento del processamento|
|STATO ESECUZIONE |<p>Esito dell’esecuzione dell’ SDK. </p><p>Possibili valori: </p><p>- IN ELABORAZIONE: Sdk in esecuzione;</p><p>- ELABORATA: Esecuzione completata con successo;</p><p>- KO: Esecuzione fallita: </p><p>- KO SPECIFICO: Esecuzione fallita per una fase/componente più rilevante della soluzione (es. ko\_gestione\_file, ko\_gestione\_validazione, ko\_invio\_ministero, etc.); </p><p>- KO GENERICO: un errore generico non controllato.</p>|
|FILE ASSOCIATI RUN|Nome del file di input elaborato dall’SDK|
|NOME FLUSSO|Valore fisso che identifica lo specifico SDK in termini di categoria e nome flusso|
|NUMERO RECORD |Numero di record del flusso input|
|NUMERO RECORD ACCETTATI|Numero validi|
|NUMERO RECORD SCARTATI|Numero scarti|
|VERSION|Versione del SDK (Access Layer e Validation Engine)|
|TIMESTAMP CREAZIONE|Timestamp creazione della info run|
|API (\*DPM)|Rappresenta L’API utilizzata per il flusso DPM (non valorizzata per gli altri flussi)|
|IDENTIFICATIVO SOGGETTO ALIMENTANTE (\*DPM)|Chiave flusso DPM (non valorizzata per gli altri flussi)|
|TIPO ATTO (\*DPM)|Chiave flusso DPM (non valorizzata per gli altri flussi)|
|NUMERO ATTO (\*DPM)|Chiave flusso DPM (non valorizzata per gli altri flussi)|
|TIPO ESITO MDS (\*DPM)|Esito della response dell’API 2 (non valorizzata per gli altri flussi) |
|DATA RICEVUTA MDS (\*DPM)|Data della response dell’API 3 (non valorizzata per gli altri flussi)|
|CODICE REGIONE|Codice Regione del Mittente|
|ANNO RIFERIMENTO|Anno cui si riferiscono i dati del flusso|
|PERIODO DI RIFERIMENTO|Rappresenta il periodo di riferimento passato in input all’SDK|
|DESCRIZIONE STATO ESECUZIONE |Specifica il messaggio breve dell’errore, maggiori informazioni saranno presenti all’interno del log applicativo|
|NOME FILE OUTPUT MDS|Nome dei file di output inviati verso MdS|
|ESITO ACQUISIZIONE FLUSSO|Codice dell’esito del processo di acquisizione del flusso su MdS. Tale campo riflette la proprietà invioFlussiReturn/listaEsitiUpload/item/esito della response della procedura **invioFlussi**. (Es IF00)|
|CODICE ERRORE INVIO FLUSSI|Codice d’errore della procedura di invio. Tale campo riflette la proprietà InvioFlussiReturn/errorCode della response della procedura **invioFlussi**|
|TESTO ERRORE INVIO FLUSSI|Descrizione codice d’errore della procedura.Tale campo riflette la proprietà InvioFlussiReturn/ errorText della response della procedura **invioFlussi**|

Inoltre, a supporto dell’entità che rappresenta lo stato dell’esecuzione, sotto la cartella /sdk/log, saranno presenti anche i file di log applicativi (aggregati giornalmente) non strutturati, nei quali saranno presenti informazioni aggiuntive, ad esempio lo StackTrace (in caso di errori).

Il naming del file, se non modificata la politica di rolling (impostazioni) è il seguente:

**SDK \_CDM-CT2.log**

## mantainer:
 Accenture SpA until January 2026