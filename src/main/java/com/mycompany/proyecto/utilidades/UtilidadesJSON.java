package com.mycompany.proyecto.utilidades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Utilidades para manejo de archivos JSON.
 * Proporciona metodos estaticos para guardar y cargar listas de objetos en formato JSON.
 * Utiliza la libreria Gson para serializacion/deserializacion.
 * 
 * @author Sistema Taller de Santa
 */
public class UtilidadesJSON {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    /**
     * Guarda una lista de objetos en un archivo JSON.
     * Si el archivo existe, lo sobrescribe. Si no existe, lo crea.
     * 
     * @param <T> Tipo generico de los objetos en la lista
     * @param lista Lista de objetos a guardar
     * @param nombreArchivo Nombre del archivo donde guardar (ej: "usuarios.json")
     */
    public static <T> void guardarEnArchivo(List<T> lista, String nombreArchivo) {
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar en archivo " + nombreArchivo + ": " + e.getMessage());
        }
    }
    
    /**
     * Carga una lista de objetos desde un archivo JSON.
     * Si el archivo no existe, retorna una lista vacia.
     * 
     * @param <T> Tipo generico de los objetos a cargar
     * @param nombreArchivo Nombre del archivo a leer
     * @param clase Clase de los objetos a deserializar
     * @return Lista de objetos cargados, o lista vacia si el archivo no existe
     */
    public static <T> List<T> cargarDesdeArchivo(String nombreArchivo, Class<T> clase) {
        List<T> lista = new ArrayList<>();
        try (FileReader reader = new FileReader(nombreArchivo)) {
            Type listType = TypeToken.getParameterized(List.class, clase).getType();
            lista = gson.fromJson(reader, listType);
            if (lista == null) {
                lista = new ArrayList<>();
            }
        } catch (IOException e) {
            // Si el archivo no existe, retornamos una lista vacía
            lista = new ArrayList<>();
        }
        return lista;
    }
}

