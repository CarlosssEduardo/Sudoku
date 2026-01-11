package br.com.dio.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static br.com.dio.service.EventoEnum.LIMPAR_ESPACO;

public class NotificadorServico {

    private final Map<EventoEnum, List<EventoOuvinte>> ouvintes = new HashMap<>(){{
        put(LIMPAR_ESPACO, new ArrayList<>());
    }};

    public void inscrever_se(final EventoEnum eventoTipo, EventoOuvinte ouvinte){
        var ouvinteSelecionado = ouvintes.get(eventoTipo);
        ouvinteSelecionado.add(ouvinte);
    }

    public void notificacao(final EventoEnum eventoTipo){
        ouvintes.get(eventoTipo).forEach(l -> l.atualizar(eventoTipo));
    }

}
