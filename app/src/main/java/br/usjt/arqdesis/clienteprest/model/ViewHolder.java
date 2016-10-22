package br.usjt.arqdesis.clienteprest.model;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by asbonato on 9/19/16.
 */
public class ViewHolder {
    private TextView nomeEmpresa, detalheEmpresa;

    public ViewHolder( TextView nomeEmpresa, TextView detalheEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
        this.detalheEmpresa = detalheEmpresa;
    }

    public TextView getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(TextView nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public TextView getDetalheEmpresa() {
        return detalheEmpresa;
    }

    public void setDetalheEmpresa(TextView detalheEmpresa) {
        this.detalheEmpresa = detalheEmpresa;
    }
}

