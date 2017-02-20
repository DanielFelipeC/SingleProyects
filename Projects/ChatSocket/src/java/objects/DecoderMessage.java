/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.io.IOException;
import java.io.Reader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author dfcastellanosc
 */
public class DecoderMessage implements Decoder.TextStream<Mensaje> {

    @Override
    public Mensaje decode(Reader reader) throws DecodeException, IOException {
        Mensaje msj = new Mensaje();
        try (JsonReader jsonReader = Json.createReader(reader)) {
            JsonObject jsonObject = jsonReader.readObject();
            msj.setNombre(jsonObject.getString("nombre"));
            msj.setMensaje(jsonObject.getString("mensaje"));
        }
        return msj;
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

}
