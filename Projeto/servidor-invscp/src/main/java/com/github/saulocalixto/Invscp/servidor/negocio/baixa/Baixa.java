package com.github.saulocalixto.Invscp.servidor.negocio.baixa;

import com.github.saulocalixto.Invscp.servidor.enumeradores.EnumMotivoBaixa;
import com.github.saulocalixto.Invscp.servidor.negocio.ModelPadrao;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Baixa extends ModelPadrao {

    private String idBem;

    private String data;

    private String observacao;

    private EnumMotivoBaixa motivo;

    public String getIdBem() {
        return idBem;
    }

    public void setIdBem(String bem) {
        this.idBem = bem;
    }

    public Date getData() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public void setData(Date data) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        this.data = format.format(data);
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public EnumMotivoBaixa getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        try {
            this.motivo = EnumMotivoBaixa.valueOf(motivo);
        } catch (Exception e) {
            this.motivo = null;
        }
    }
}
