package br.usjt.arqdesis.clienteprest.model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import br.usjt.arqdesis.clienteprest.R;

/**
 * Created by igorj on 21/10/2016.
 */

public class CNPJAdapter extends BaseAdapter  {

    Activity context;
    List<ConsultaCNPJ> cnpjs;



    public CNPJAdapter(Activity context, List<ConsultaCNPJ> cnpjs){
        this.context = context;
        this.cnpjs = cnpjs;
    }

    @Override
    public int getCount() {
        return cnpjs.size();
    }

    @Override
    public Object getItem(int position) {
        if(position >= 0 && position < cnpjs.size())
            return cnpjs.get(position);
        else
            return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //o list view recicla os layouts para melhor performance
        //o layout reciclado vem no parametro convert view
        View view = convertView;
        //se nao recebeu um layout para reutilizar deve inflar um
        if(view == null) {
            //um inflater transforma um layout em uma view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.linha_cliente, parent, false);

            TextView nomeEmpresa = (TextView) view.findViewById(R.id.nome_empresa);
            TextView detalheEmpresa = (TextView) view.findViewById(R.id.detalhe_empresa);

            //faz cache dos widgets instanciados na tag da view para reusar quando houver reciclagem
            view.setTag(new ViewHolder(nomeEmpresa,detalheEmpresa));
        }
        //usa os widgets cacheados na view reciclada
        ViewHolder holder = (ViewHolder)view.getTag();
        //carrega os novos valores
        //Drawable drawable = Util.getDrawable(context, clientes.get(position).getFoto());
      //holder.getFotoCliente().setImageDrawable(drawable);
        Locale locale = new Locale("pt", "BR");
        holder.getNomeEmpresa().setText(cnpjs.get(position).getNome());
        holder.getDetalheEmpresa().setText(String.format("%s - %s", cnpjs.get(position).getDataSituacao()
                , cnpjs.get(position).getMotivoSituacao()
                , cnpjs.get(position).getTipo()
                , cnpjs.get(position).getNome()
                , cnpjs.get(position).getTelefone()
                , cnpjs.get(position).getSituacao()
                , cnpjs.get(position).getAbertura()
                , cnpjs.get(position).getNaturezaZuridica()
                , cnpjs.get(position).getCnpj()
                , cnpjs.get(position).getUltimaAtualizacao()
                , cnpjs.get(position).getStatus()
                , cnpjs.get(position).getFantasia()
                , cnpjs.get(position).getLogradouro()
                , cnpjs.get(position).getNumero()
                , cnpjs.get(position).getComplemento()
                , cnpjs.get(position).getCep()
                , cnpjs.get(position).getBairro()
                , cnpjs.get(position).getMunicipio()
                , cnpjs.get(position).getUf()
                , cnpjs.get(position).getEmail()
                , cnpjs.get(position).getEfr()
                , cnpjs.get(position).getSituacaoEspecial()
                , cnpjs.get(position).getDataSituacaoEspecial()
                , cnpjs.get(position).getAtividadePrincipal()
                , cnpjs.get(position).getAtividadesSecundarias()));

        return view;
    }


    public void updateCNPJList(List<ConsultaCNPJ> newlist) {
        cnpjs.clear();
        cnpjs.addAll(newlist);
        this.notifyDataSetChanged();
    }
}
