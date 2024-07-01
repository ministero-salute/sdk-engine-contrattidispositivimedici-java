/* SPDX-License-Identifier: BSD-3-Clause */
 
package it.mds.sdk.flusso.ct2.parser.regole;

import com.opencsv.bean.CsvBindByPosition;
import it.mds.sdk.libreriaregole.dtos.RecordDtoGenerico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordDtoCt2 extends RecordDtoGenerico {

    @CsvBindByPosition(position = 0)
    private String codiceRegione;

    @CsvBindByPosition(position = 1)
    private String codiceAziendaSanitariaContraente;

    @CsvBindByPosition(position = 2)
    private String identificativoDelContratto;

    @CsvBindByPosition(position = 3)
    private String tipologiaDiContratto;

    @CsvBindByPosition(position = 4)
    private String annoStipulaContratto;

    @CsvBindByPosition(position = 5)
    private String meseStipulaContratto;

    @CsvBindByPosition(position = 6)
    private String giornoStipulaContratto;

    @CsvBindByPosition(position = 7)
    private String durataDelContratto;

    @CsvBindByPosition(position = 8)
    private String formaDiNegoziazione;

    @CsvBindByPosition(position = 9)
    private String ambitoDiValenzaDelContratto;

    @CsvBindByPosition(position = 10)
    private String codiceCig;

    @CsvBindByPosition(position = 11)
    private String tipoDispositivoMedico;

    @CsvBindByPosition(position = 12)
    private String identificativoDiIscrizioneInBancaDatiRepertorio;

    @CsvBindByPosition(position = 13)
    private String numeroDiPezziPresentiNellaConfezioneMinimaDiVendita;

    @CsvBindByPosition(position = 14)
    private String denominazioneDelFornitore;

    @CsvBindByPosition(position = 15)
    private String partitaIvaDelFornitore;

    @CsvBindByPosition(position = 16)
    private String quantitaAggiudicata;

    @CsvBindByPosition(position = 17)
    private String quantitaContrattualizzata;

    @CsvBindByPosition(position = 18)
    private String prezzoUnitarioAggiudicato;

    @CsvBindByPosition(position = 19)
    private String aliquotaIva;

    @CsvBindByPosition(position = 20)
    private String serviziAccessori;

    @CsvBindByPosition(position = 21)
    private String contoDeposito;

    @CsvBindByPosition(position = 22)
    private String voceDiImputazioneNelModelloCE;

    @CsvBindByPosition(position = 23)
    private String progressivoRiga;

    @CsvBindByPosition(position = 24)
    private String tipoOperazione;
}
