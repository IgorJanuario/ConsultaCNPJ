package br.usjt.arqdesis.clienteprest.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.usjt.arqdesis.clienteprest.R;
import br.usjt.arqdesis.clienteprest.model.CNPJAdapter;
import br.usjt.arqdesis.clienteprest.model.ConsultaCNPJ;
import br.usjt.arqdesis.clienteprest.util.APICNPJ;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by igorj on 21/10/2016.
 */

public class ListaCNPJActivity extends AppCompatActivity {

    public static final String DATASITUACAO = "br.usjt.arqdesis.clientep2.datasituacao";
    public static final String MOTIVOSITUACAO = "br.usjt.arqdesis.clientep2.motivosituacao";
    public static final String TIPO = "br.usjt.arqdesis.clientep2.tipo";
    public static final String NOME = "br.usjt.arqdesis.clientep2.nome";
    public static final String TELEFONE = "br.usjt.arqdesis.clientep2.telefone";
    public static final String SITUACAO = "br.usjt.arqdesis.clientep2.situacao";
    public static final String ABERTURA = "br.usjt.arqdesis.clientep2.abertura";
    public static final String NATUREZAZURIDICA = "br.usjt.arqdesis.clientep2.naturezazuridica";
    public static final String CNPJ = "br.usjt.arqdesis.clientep2.cnpj";
    public static final String ULTIMAATUALIZACAO = "br.usjt.arqdesis.clientep2.ultimaatualizacao";
    public static final String STATUS = "br.usjt.arqdesis.clientep2.status";
    public static final String FANTASIA = "br.usjt.arqdesis.clientep2.fantasia";
    public static final String LOGRADOURO = "br.usjt.arqdesis.clientep2.logradouro";
    public static final String NUMERO = "br.usjt.arqdesis.clientep2.numero";
    public static final String COMPLEMENTO = "br.usjt.arqdesis.clientep2.complemento";
    public static final String CEP = "br.usjt.arqdesis.clientep2.cep";
    public static final String BAIRRO = "br.usjt.arqdesis.clientep2.bairro";
    public static final String MUNICIPIO = "br.usjt.arqdesis.clientep2.municipio";
    public static final String UF = "br.usjt.arqdesis.clientep2.uf";
    public static final String EMAIL = "br.usjt.arqdesis.clientep2.email";
    public static final String EFR = "br.usjt.arqdesis.clientep2.efr";
    public static final String SITUACAOESPECIAL = "br.usjt.arqdesis.clientep2.situacaoespecial";
    public static final String DATASITUACAOESPECIAL = "br.usjt.arqdesis.clientep2.datasituacaoespecial";
  //  public static final String ATIVIDADEPRINCIPAL = "br.usjt.arqdesis.clientep2.atividadeprincipal";
  //  public static final String ATIVIDADESSECUNDARIAS = "br.usjt.arqdesis.clientep2.atividadessecundarias";

    Activity atividade;
    List<ConsultaCNPJ> lista;
    Callback callBackCNPJ;
    CNPJAdapter adapter;
    ListView listView;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);

        lista = new ArrayList<ConsultaCNPJ>();

        configurarCallbackCNPJ();

        atividade = this;
        Intent intent = getIntent();
        String chave = intent.getStringExtra(MainActivity.CHAVE);

        //lista = Data.buscaClientes(chave);

        adapter = new CNPJAdapter(this, lista);
        listView = (ListView) findViewById(R.id.lista_clientes);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // manda para a tela de detalhe
                Intent intent = new Intent(atividade, DetalheCNPJActivity.class);
                intent.putExtra(DATASITUACAO, lista.get(position).getDataSituacao());
                intent.putExtra(MOTIVOSITUACAO, lista.get(position).getMotivoSituacao());
                intent.putExtra(TIPO, lista.get(position).getTipo());
                intent.putExtra(NOME, lista.get(position).getNome());
                intent.putExtra(TELEFONE, lista.get(position).getTelefone());
                intent.putExtra(SITUACAO, lista.get(position).getSituacao());
                intent.putExtra(ABERTURA, lista.get(position).getAbertura());
                intent.putExtra(NATUREZAZURIDICA, lista.get(position).getNaturezaZuridica());
                intent.putExtra(CNPJ, lista.get(position).getCnpj());
                intent.putExtra(ULTIMAATUALIZACAO, lista.get(position).getUltimaAtualizacao());
                intent.putExtra(STATUS, lista.get(position).getStatus());
                intent.putExtra(FANTASIA, lista.get(position).getFantasia());
                intent.putExtra(LOGRADOURO, lista.get(position).getLogradouro());
                intent.putExtra(NUMERO, lista.get(position).getNumero());
                intent.putExtra(COMPLEMENTO, lista.get(position).getComplemento());
                intent.putExtra(CEP, lista.get(position).getCep());
                intent.putExtra(BAIRRO, lista.get(position).getBairro());
                intent.putExtra(MUNICIPIO, lista.get(position).getMunicipio());
                intent.putExtra(UF, lista.get(position).getUf());
                intent.putExtra(EMAIL, lista.get(position).getEmail());
                intent.putExtra(EFR, lista.get(position).getEfr());
                intent.putExtra(SITUACAOESPECIAL, lista.get(position).getSituacaoEspecial());
                intent.putExtra(DATASITUACAOESPECIAL, lista.get(position).getDataSituacaoEspecial());
             //   intent.putExtra(ATIVIDADEPRINCIPAL, lista.get(position).getAtividadePrincipal());
             //   intent.putExtra(ATIVIDADESSECUNDARIAS, lista.get(position).getAtividadesSecundarias());

                startActivity(intent);

            }

        });

        Toast.makeText(ListaCNPJActivity.this,"Carregando...",Toast.LENGTH_SHORT).show();

        if(chave !=null && chave.length()>0){
            new APICNPJ().getRestService().getCNPJ(chave,callBackCNPJ);
        }


    }

    private void configurarCallbackCNPJ() {

        callBackCNPJ = new Callback<List<ConsultaCNPJ>>() {

            @Override public void success(List<ConsultaCNPJ> list, Response response) {

                if(response.getStatus()==200) {
                    adapter.updateCNPJList(list);
                }else{
                    Toast.makeText(ListaCNPJActivity.this,"Falha na comunicação com o servidor!",Toast.LENGTH_LONG).show();
                }

            }

            @Override public void failure(RetrofitError error) {

                Toast.makeText(ListaCNPJActivity.this,"Falha na comunicação com o servidor!",Toast.LENGTH_LONG).show();

                Log.e("RETROFIT", "Error:"+error.getMessage());
            }
        };
    }
}
