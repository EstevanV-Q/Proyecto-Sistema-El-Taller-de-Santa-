package com.mycompany.proyecto;

import com.mycompany.proyecto.gestores.GestorAsignaciones;
import com.mycompany.proyecto.gestores.GestorNiños;
import com.mycompany.proyecto.gestores.GestorRegalos;
import com.mycompany.proyecto.gestores.GestorUsuarios;
import com.mycompany.proyecto.modelo.Asignacion;
import com.mycompany.proyecto.modelo.Niño;
import com.mycompany.proyecto.modelo.Regalo;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal del sistema "El Taller de Santa"
 * Sistema de gestión de regalos navideños y niños
 */
public class TallerSanta {
    private static Scanner scanner = new Scanner(System.in);
    private static GestorUsuarios gestorUsuarios;
    private static GestorRegalos gestorRegalos;
    private static GestorNiños gestorNiños;
    private static GestorAsignaciones gestorAsignaciones;
    private static boolean sesionIniciada = false;
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   BIENVENIDO AL TALLER DE SANTA");
        System.out.println("========================================\n");
        
        // Inicializar gestores (cargarán automáticamente los archivos JSON)
        gestorUsuarios = new GestorUsuarios();
        gestorRegalos = new GestorRegalos();
        gestorNiños = new GestorNiños();
        gestorAsignaciones = new GestorAsignaciones();
        
        // Menú principal
        mostrarMenuPrincipal();
    }
    
    /**
     * Muestra el menú principal del sistema
     */
    private static void mostrarMenuPrincipal() {
        while (true) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Registro de Usuario");
            System.out.println("2. Iniciar Sesion");
            System.out.println("3. Salir");
            System.out.print("\nSeleccione una opcion: ");
            
            try {
                int opcion = Integer.parseInt(scanner.nextLine().trim());
                
                switch (opcion) {
                    case 1:
                        registrarUsuario();
                        break;
                    case 2:
                        if (iniciarSesion()) {
                            mostrarMenuSistema();
                        }
                        break;
                    case 3:
                        System.out.println("\n¡Gracias por usar El Taller de Santa!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("\n❌ Opcion invalida. Por favor, seleccione 1, 2 o 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n❌ Por favor, ingrese un numero valido.");
            } catch (Exception e) {
                System.out.println("\n❌ Error: " + e.getMessage());
            }
        }
    }
    
    /**
     * Registra un nuevo usuario
     */
    private static void registrarUsuario() {
        System.out.println("\n=== REGISTRO DE USUARIO ===");
        System.out.print("Nombre de usuario: ");
        String nombreUsuario = scanner.nextLine().trim();
        System.out.print("Contrasena: ");
        String Contrasena = scanner.nextLine().trim();
        
        if (gestorUsuarios.registrarUsuario(nombreUsuario, Contrasena)) {
            System.out.println("\n✅ Usuario registrado exitosamente.");
        } else {
                System.out.println("\n❌ Error al registrar usuario. El nombre de usuario ya existe o los datos son invalidos.");
        }
    }
    
    /**
     * Inicia sesión en el sistema
     */
    private static boolean iniciarSesion() {
        System.out.println("\n=== INICIAR SESION ===");
        System.out.print("Nombre de usuario: ");
        String nombreUsuario = scanner.nextLine().trim();
        System.out.print("Contrasena: ");
        String Contrasena = scanner.nextLine().trim();
        
        if (gestorUsuarios.validarCredenciales(nombreUsuario, Contrasena)) {
            sesionIniciada = true;
            System.out.println("\n✅ Sesion iniciada correctamente.");
            return true;
        } else {
            System.out.println("\n❌ Credenciales incorrectas.");
            return false;
        }
    }
    
    /**
     * Muestra el menú del sistema (después de iniciar sesión)
     */
    private static void mostrarMenuSistema() {
        while (sesionIniciada) {
            System.out.println("\n=== MENU DEL SISTEMA ===");
            System.out.println("1. Gestion de Regalos");
            System.out.println("2. Gestion de Ninos");
            System.out.println("3. Asignacion de Regalos");
            System.out.println("4. Reportes");
            System.out.println("5. Cerrar Sesion");
            System.out.print("\nSeleccione una opcion: ");
            
            try {
                int opcion = Integer.parseInt(scanner.nextLine().trim());
                
                switch (opcion) {
                    case 1:
                        menuGestionRegalos();
                        break;
                    case 2:
                        menuGestionNiños();
                        break;
                    case 3:
                        menuAsignacionRegalos();
                        break;
                    case 4:
                        menuReportes();
                        break;
                    case 5:
                        sesionIniciada = false;
                        System.out.println("\n✅ Sesion cerrada correctamente.");
                        return;
                    default:
                        System.out.println("\n❌ Opcion invalida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n❌ Por favor, ingrese un numero valido.");
            } catch (Exception e) {
                System.out.println("\n❌ Error: " + e.getMessage());
            }
        }
    }
    
    /**
     * Menú de gestión de regalos
     */
    private static void menuGestionRegalos() {
        while (true) {
            System.out.println("\n=== GESTION DE REGALOS ===");
            System.out.println("1. Registrar Regalo");
            System.out.println("2. Modificar Regalo");
            System.out.println("3. Eliminar Regalo");
            System.out.println("4. Reabastecer Inventario");
            System.out.println("5. Consultar Regalo por Codigo");
            System.out.println("6. Volver al Menu Principal");
            System.out.print("\nSeleccione una opcion: ");
            
            try {
                int opcion = Integer.parseInt(scanner.nextLine().trim());
                
                switch (opcion) {
                    case 1:
                        registrarRegalo();
                        break;
                    case 2:
                        modificarRegalo();
                        break;
                    case 3:
                        eliminarRegalo();
                        break;
                    case 4:
                        reabastecerInventario();
                        break;
                    case 5:
                        consultarRegalo();
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("\n❌ Opcion invalida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n❌ Por favor, ingrese un numero valido.");
            }
        }
    }
    
    /**
     * Registra un nuevo regalo
     */
    private static void registrarRegalo() {
        System.out.println("\n=== REGISTRAR REGALO ===");
        System.out.print("Codigo del regalo: ");
        String codigo = scanner.nextLine().trim();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("Descripcion: ");
        String descripcion = scanner.nextLine().trim();
        System.out.print("Marca: ");
        String marca = scanner.nextLine().trim();
        System.out.print("Cantidad disponible: ");
        
        try {
            int cantidad = Integer.parseInt(scanner.nextLine().trim());
            if (gestorRegalos.registrarRegalo(codigo, nombre, descripcion, marca, cantidad)) {
                System.out.println("\n✅ Regalo registrado exitosamente.");
            } else {
                System.out.println("\n❌ Error al registrar regalo. El codigo ya existe o los datos son invalidos.");
            }
        } catch (NumberFormatException e) {
                System.out.println("\n❌ La cantidad debe ser un numero valido.");
        }
    }
    
    /**
     * Modifica un regalo existente
     */
    private static void modificarRegalo() {
        System.out.println("\n=== MODIFICAR REGALO ===");
        System.out.print("Codigo del regalo a modificar: ");
        String codigo = scanner.nextLine().trim();
        
        Regalo regalo = gestorRegalos.buscarPorCodigo(codigo);
        if (regalo == null) {
            System.out.println("\n❌ Regalo no encontrado.");
            return;
        }
        
        System.out.println("\nRegalo actual:");
        System.out.println("Nombre: " + regalo.getNombre());
        System.out.println("Descripción: " + regalo.getDescripcion());
        System.out.println("Marca: " + regalo.getMarca());
        System.out.println("Cantidad: " + regalo.getCantidadDisponible());
        
        System.out.println("\nIngrese los nuevos datos (presione Enter para mantener el valor actual):");
        System.out.print("Nuevo nombre: ");
        String nuevoNombre = scanner.nextLine().trim();
        System.out.print("Nueva descripcion: ");
        String nuevaDescripcion = scanner.nextLine().trim();
        System.out.print("Nueva marca: ");
        String nuevaMarca = scanner.nextLine().trim();
        System.out.print("Nueva cantidad: ");
        String cantidadStr = scanner.nextLine().trim();
        
        Integer nuevaCantidad = null;
        if (!cantidadStr.isEmpty()) {
            try {
                nuevaCantidad = Integer.parseInt(cantidadStr);
            } catch (NumberFormatException e) {
                System.out.println("\n❌ Cantidad invalida. Se mantendra la cantidad actual.");
            }
        }
        
        if (gestorRegalos.modificarRegalo(codigo, 
                nuevoNombre.isEmpty() ? null : nuevoNombre,
                nuevaDescripcion.isEmpty() ? null : nuevaDescripcion,
                nuevaMarca.isEmpty() ? null : nuevaMarca,
                nuevaCantidad)) {
            System.out.println("\n✅ Regalo modificado exitosamente.");
        } else {
            System.out.println("\n❌ Error al modificar regalo.");
        }
    }
    
    /**
     * Elimina un regalo
     */
    private static void eliminarRegalo() {
        System.out.println("\n=== ELIMINAR REGALO ===");
        System.out.print("Codigo del regalo a eliminar: ");
        String codigo = scanner.nextLine().trim();
        
        if (gestorRegalos.eliminarRegalo(codigo, gestorAsignaciones)) {
            System.out.println("\n✅ Regalo eliminado exitosamente.");
        } else {
            System.out.println("\n❌ Error al eliminar regalo. El regalo no existe o tiene ninos asignados.");
        }
    }
    
    /**
     * Reabastece el inventario de un regalo
     */
    private static void reabastecerInventario() {
        System.out.println("\n=== REABASTECER INVENTARIO ===");
        System.out.print("Codigo del regalo: ");
        String codigo = scanner.nextLine().trim();
        System.out.print("Cantidad a agregar: ");
        
        try {
            int cantidad = Integer.parseInt(scanner.nextLine().trim());
            if (gestorRegalos.reabastecerInventario(codigo, cantidad)) {
                System.out.println("\n✅ Inventario reabastecido exitosamente.");
            } else {
                System.out.println("\n❌ Error al reabastecer. El regalo no existe o la cantidad es invalida.");
            }
        } catch (NumberFormatException e) {
                System.out.println("\n❌ La cantidad debe ser un numero valido.");
        }
    }
    
    /**
     * Consulta un regalo por código
     */
    private static void consultarRegalo() {
        System.out.println("\n=== CONSULTAR REGALO ===");
        System.out.print("Codigo del regalo: ");
        String codigo = scanner.nextLine().trim();
        
        Regalo regalo = gestorRegalos.buscarPorCodigo(codigo);
        if (regalo != null) {
            System.out.println("\n=== INFORMACION DEL REGALO ===");
            System.out.println("Codigo: " + regalo.getCodigo());
            System.out.println("Nombre: " + regalo.getNombre());
            System.out.println("Descripcion: " + regalo.getDescripcion());
            System.out.println("Marca: " + regalo.getMarca());
            System.out.println("Cantidad disponible: " + regalo.getCantidadDisponible());
        } else {
            System.out.println("\n❌ Regalo no encontrado.");
        }
    }
    
    /**
     * Menú de gestión de niños
     */
    private static void menuGestionNiños() {
        while (true) {
            System.out.println("\n=== GESTION DE NINOS ===");
            System.out.println("1. Registrar Nino");
            System.out.println("2. Modificar Nino");
            System.out.println("3. Eliminar Nino");
            System.out.println("4. Consultar Nino por Identificacion");
            System.out.println("5. Volver al Menu Principal");
            System.out.print("\nSeleccione una opcion: ");
            
            try {
                int opcion = Integer.parseInt(scanner.nextLine().trim());
                
                switch (opcion) {
                    case 1:
                        registrarNiño();
                        break;
                    case 2:
                        modificarNiño();
                        break;
                    case 3:
                        eliminarNiño();
                        break;
                    case 4:
                        consultarNiño();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("\n❌ Opcion invalida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n❌ Por favor, ingrese un numero valido.");
            }
        }
    }
    
    /**
     * Registra un nuevo niño
     */
    private static void registrarNiño() {
        System.out.println("\n=== REGISTRAR NINO ===");
        System.out.print("Identificacion: ");
        String identificacion = scanner.nextLine().trim();
        System.out.print("Nombre completo: ");
        String nombreCompleto = scanner.nextLine().trim();
        System.out.print("Edad: ");
        
        try {
            int edad = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Ciudad: ");
            String ciudad = scanner.nextLine().trim();
            System.out.print("Direccion detallada: ");
            String direccion = scanner.nextLine().trim();
            
            if (gestorNiños.registrarNiño(identificacion, nombreCompleto, edad, ciudad, direccion)) {
                System.out.println("\n✅ Nino registrado exitosamente.");
            } else {
                System.out.println("\n❌ Error al registrar nino. La identificacion ya existe o los datos son invalidos.");
            }
        } catch (NumberFormatException e) {
            System.out.println("\n❌ La edad debe ser un numero valido.");
        }
    }
    
    /**
     * Modifica un niño existente
     */
    private static void modificarNiño() {
        System.out.println("\n=== MODIFICAR NINO ===");
        System.out.print("Identificacion del nino a modificar: ");
        String identificacion = scanner.nextLine().trim();
        
        Niño niño = gestorNiños.buscarPorIdentificacion(identificacion);
        if (niño == null) {
            System.out.println("\n❌ Nino no encontrado.");
            return;
        }
        
        System.out.println("\nNino actual:");
        System.out.println("Nombre: " + niño.getNombreCompleto());
        System.out.println("Edad: " + niño.getEdad());
        System.out.println("Ciudad: " + niño.getCiudad());
        System.out.println("Direccion: " + niño.getDireccionDetallada());
        
        System.out.println("\nIngrese los nuevos datos (presione Enter para mantener el valor actual):");
        System.out.print("Nuevo nombre: ");
        String nuevoNombre = scanner.nextLine().trim();
        System.out.print("Nueva edad: ");
        String edadStr = scanner.nextLine().trim();
        System.out.print("Nueva ciudad: ");
        String nuevaCiudad = scanner.nextLine().trim();
        System.out.print("Nueva direccion: ");
        String nuevaDireccion = scanner.nextLine().trim();
        
        Integer nuevaEdad = null;
        if (!edadStr.isEmpty()) {
            try {
                nuevaEdad = Integer.parseInt(edadStr);
            } catch (NumberFormatException e) {
                System.out.println("\n❌ Edad invalida. Se mantendra la edad actual.");
            }
        }
        
        if (gestorNiños.modificarNiño(identificacion,
                nuevoNombre.isEmpty() ? null : nuevoNombre,
                nuevaEdad,
                nuevaCiudad.isEmpty() ? null : nuevaCiudad,
                nuevaDireccion.isEmpty() ? null : nuevaDireccion)) {
            System.out.println("\n✅ Nino modificado exitosamente.");
        } else {
            System.out.println("\n❌ Error al modificar nino.");
        }
    }
    
    /**
     * Elimina un niño
     */
    private static void eliminarNiño() {
        System.out.println("\n=== ELIMINAR NINO ===");
        System.out.print("Identificacion del nino a eliminar: ");
        String identificacion = scanner.nextLine().trim();
        
        if (gestorNiños.eliminarNiño(identificacion, gestorAsignaciones)) {
            System.out.println("\n✅ Nino eliminado exitosamente.");
        } else {
            System.out.println("\n❌ Error al eliminar nino. El nino no existe o tiene un regalo asignado.");
        }
    }
    
    /**
     * Consulta un niño por identificación
     */
    private static void consultarNiño() {
        System.out.println("\n=== CONSULTAR NINO ===");
        System.out.print("Identificacion del nino: ");
        String identificacion = scanner.nextLine().trim();
        
        Niño niño = gestorNiños.buscarPorIdentificacion(identificacion);
        if (niño != null) {
            System.out.println("\n=== INFORMACION DEL NINO ===");
            System.out.println("Identificacion: " + niño.getIdentificacion());
            System.out.println("Nombre completo: " + niño.getNombreCompleto());
            System.out.println("Edad: " + niño.getEdad());
            System.out.println("Ciudad: " + niño.getCiudad());
            System.out.println("Direccion detallada: " + niño.getDireccionDetallada());
        } else {
            System.out.println("\n❌ Nino no encontrado.");
        }
    }
    
    /**
     * Menú de asignación de regalos
     */
    private static void menuAsignacionRegalos() {
        while (true) {
            System.out.println("\n=== ASIGNACION DE REGALOS ===");
            System.out.println("1. Asignar Regalo a Nino");
            System.out.println("2. Buscar Asignacion por Identificacion del Nino");
            System.out.println("3. Volver al Menu Principal");
            System.out.print("\nSeleccione una opcion: ");
            
            try {
                int opcion = Integer.parseInt(scanner.nextLine().trim());
                
                switch (opcion) {
                    case 1:
                        asignarRegalo();
                        break;
                    case 2:
                        buscarAsignacion();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("\n❌ Opcion invalida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n❌ Por favor, ingrese un numero valido.");
            }
        }
    }
    
    /**
     * Asigna un regalo a un niño
     */
    private static void asignarRegalo() {
        System.out.println("\n=== ASIGNAR REGALO A NINO ===");
        System.out.print("Identificacion del nino: ");
        String identificacion = scanner.nextLine().trim();
        
        Niño niño = gestorNiños.buscarPorIdentificacion(identificacion);
        if (niño == null) {
            System.out.println("\n❌ Nino no encontrado.");
            return;
        }
        
        // Verificar si ya tiene asignación
        if (gestorAsignaciones.tieneAsignacionPorIdentificacion(identificacion)) {
            System.out.println("\n❌ Este nino ya tiene un regalo asignado.");
            return;
        }
        
        System.out.print("Codigo del regalo: ");
        String codigoRegalo = scanner.nextLine().trim();
        
        Regalo regalo = gestorRegalos.buscarPorCodigo(codigoRegalo);
        if (regalo == null) {
            System.out.println("\n❌ Regalo no encontrado.");
            return;
        }
        
        if (regalo.getCantidadDisponible() <= 0) {
            System.out.println("\n❌ No hay disponibilidad de este regalo.");
            return;
        }
        
        if (gestorAsignaciones.asignarRegalo(identificacion, regalo, gestorRegalos)) {
            System.out.println("\n✅ Regalo asignado exitosamente a " + niño.getNombreCompleto() + ".");
        } else {
            System.out.println("\n❌ Error al asignar regalo.");
        }
    }
    
    /**
     * Busca asignación por identificación del niño
     */
    private static void buscarAsignacion() {
        System.out.println("\n=== BUSCAR ASIGNACION ===");
        System.out.print("Identificacion del nino: ");
        String identificacion = scanner.nextLine().trim();
        
        Asignacion asignacion = gestorAsignaciones.buscarPorIdentificacion(identificacion);
        if (asignacion != null) {
            System.out.println("\n=== ASIGNACION ENCONTRADA ===");
            System.out.println("Identificacion del nino: " + asignacion.getIdentificacionNiño());
            System.out.println("Codigo del regalo: " + asignacion.getCodigoRegalo());
            System.out.println("Nombre del regalo: " + asignacion.getNombreRegalo());
            System.out.println("Marca del regalo: " + asignacion.getMarcaRegalo());
        } else {
            System.out.println("\n❌ El nino no posee asignaciones.");
        }
    }
    
    /**
     * Menú de reportes
     */
    private static void menuReportes() {
        while (true) {
            System.out.println("\n=== REPORTES ===");
            System.out.println("1. Inventario actual de regalos");
            System.out.println("2. Listado completo de ninos registrados");
            System.out.println("3. Detalle de regalos asignados a cada nino");
            System.out.println("4. Listado de ninos sin regalo asignado");
            System.out.println("5. Regalos por marca (guardar en archivo)");
            System.out.println("6. Volver al Menu Principal");
            System.out.print("\nSeleccione una opcion: ");
            
            try {
                int opcion = Integer.parseInt(scanner.nextLine().trim());
                
                switch (opcion) {
                    case 1:
                        reporteInventario();
                        break;
                    case 2:
                        reporteNiñosRegistrados();
                        break;
                    case 3:
                        reporteAsignaciones();
                        break;
                    case 4:
                        reporteNiñosSinRegalo();
                        break;
                    case 5:
                        reporteRegalosPorMarca();
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("\n❌ Opcion invalida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n❌ Por favor, ingrese un numero valido.");
            }
        }
    }
    
    /**
     * Reporte 1: Inventario actual de regalos
     */
    private static void reporteInventario() {
        System.out.println("\n=== REPORTE: INVENTARIO ACTUAL DE REGALOS ===");
        List<Regalo> regalos = gestorRegalos.getRegalos();
        
        if (regalos.isEmpty()) {
            System.out.println("\nNo hay regalos registrados en el inventario.");
        } else {
            System.out.println(String.format("%-15s %-30s %-20s %-15s %-10s", 
                    "CODIGO", "NOMBRE", "MARCA", "DESCRIPCION", "CANTIDAD"));
            System.out.println("--------------------------------------------------------------------------------");
            for (Regalo regalo : regalos) {
                System.out.println(String.format("%-15s %-30s %-20s %-15s %-10d",
                        regalo.getCodigo(),
                        regalo.getNombre(),
                        regalo.getMarca(),
                        regalo.getDescripcion().length() > 15 ? 
                            regalo.getDescripcion().substring(0, 15) + "..." : regalo.getDescripcion(),
                        regalo.getCantidadDisponible()));
            }
            System.out.println("\nTotal de regalos: " + regalos.size());
        }
    }
    
    /**
     * Reporte 2: Listado completo de niños registrados
     */
    private static void reporteNiñosRegistrados() {
        System.out.println("\n=== REPORTE: LISTADO COMPLETO DE NINOS REGISTRADOS ===");
        List<Niño> niños = gestorNiños.getNiños();
        
        if (niños.isEmpty()) {
            System.out.println("\nNo hay ninos registrados.");
        } else {
            System.out.println(String.format("%-15s %-30s %-5s %-20s %-30s",
                    "IDENTIFICACION", "NOMBRE COMPLETO", "EDAD", "CIUDAD", "DIRECCION"));
            System.out.println("--------------------------------------------------------------------------------");
            for (Niño niño : niños) {
                System.out.println(String.format("%-15s %-30s %-5d %-20s %-30s",
                        niño.getIdentificacion(),
                        niño.getNombreCompleto(),
                        niño.getEdad(),
                        niño.getCiudad(),
                        niño.getDireccionDetallada().length() > 30 ? 
                            niño.getDireccionDetallada().substring(0, 30) + "..." : niño.getDireccionDetallada()));
            }
            System.out.println("\nTotal de ninos registrados: " + niños.size());
        }
    }
    
    /**
     * Reporte 3: Detalle de regalos asignados a cada niño
     */
    private static void reporteAsignaciones() {
        System.out.println("\n=== REPORTE: DETALLE DE REGALOS ASIGNADOS ===");
        List<Asignacion> asignaciones = gestorAsignaciones.getAsignaciones();
        
        if (asignaciones.isEmpty()) {
            System.out.println("\nNo hay asignaciones registradas.");
        } else {
            System.out.println(String.format("%-15s %-30s %-15s %-30s %-20s",
                    "ID NINO", "NOMBRE NINO", "CODIGO REGALO", "NOMBRE REGALO", "MARCA"));
            System.out.println("--------------------------------------------------------------------------------");
            for (Asignacion asignacion : asignaciones) {
                Niño niño = gestorNiños.buscarPorIdentificacion(asignacion.getIdentificacionNiño());
                String nombreNiño = niño != null ? niño.getNombreCompleto() : "No encontrado";
                System.out.println(String.format("%-15s %-30s %-15s %-30s %-20s",
                        asignacion.getIdentificacionNiño(),
                        nombreNiño,
                        asignacion.getCodigoRegalo(),
                        asignacion.getNombreRegalo(),
                        asignacion.getMarcaRegalo()));
            }
            System.out.println("\nTotal de asignaciones: " + asignaciones.size());
        }
    }
    
    /**
     * Reporte 4: Listado de niños sin regalo asignado
     */
    private static void reporteNiñosSinRegalo() {
        System.out.println("\n=== REPORTE: NINOS SIN REGALO ASIGNADO ===");
        List<Niño> todosLosNiños = gestorNiños.getNiños();
        List<String> niñosConAsignacion = gestorAsignaciones.getNiñosConAsignacion();
        
        List<Niño> niñosSinRegalo = new java.util.ArrayList<>();
        for (Niño niño : todosLosNiños) {
            if (!niñosConAsignacion.contains(niño.getIdentificacion())) {
                niñosSinRegalo.add(niño);
            }
        }
        
        if (niñosSinRegalo.isEmpty()) {
            System.out.println("\nTodos los ninos tienen regalos asignados.");
        } else {
            System.out.println(String.format("%-15s %-30s %-5s %-20s",
                    "IDENTIFICACION", "NOMBRE COMPLETO", "EDAD", "CIUDAD"));
            System.out.println("--------------------------------------------------------------------------------");
            for (Niño niño : niñosSinRegalo) {
                System.out.println(String.format("%-15s %-30s %-5d %-20s",
                        niño.getIdentificacion(),
                        niño.getNombreCompleto(),
                        niño.getEdad(),
                        niño.getCiudad()));
            }
            System.out.println("\nTotal de ninos sin regalo: " + niñosSinRegalo.size());
        }
    }
    
    /**
     * Reporte 5: Regalos por marca (guardar en archivo)
     */
    private static void reporteRegalosPorMarca() {
        System.out.println("\n=== REPORTE: REGALOS POR MARCA ===");
        System.out.print("Ingrese la marca a buscar: ");
        String marca = scanner.nextLine().trim();
        
        List<Regalo> regalos = gestorRegalos.getRegalosPorMarca(marca);
        
        if (regalos.isEmpty()) {
            System.out.println("\nNo se encontraron regalos de la marca: " + marca);
        } else {
            String nombreArchivo = "regalos_" + marca.replaceAll("[^a-zA-Z0-9]", "_") + ".txt";
            
            try (FileWriter writer = new FileWriter(nombreArchivo)) {
                writer.write("=== REPORTE DE REGALOS POR MARCA ===\n");
                writer.write("Marca: " + marca + "\n");
                writer.write("Fecha: " + java.time.LocalDateTime.now() + "\n\n");
                writer.write(String.format("%-15s %-30s %-40s %-10s\n",
                        "CODIGO", "NOMBRE", "DESCRIPCION", "CANTIDAD"));
                writer.write("--------------------------------------------------------------------------------\n");
                
                for (Regalo regalo : regalos) {
                    writer.write(String.format("%-15s %-30s %-40s %-10d\n",
                            regalo.getCodigo(),
                            regalo.getNombre(),
                            regalo.getDescripcion(),
                            regalo.getCantidadDisponible()));
                }
                
                writer.write("\nTotal de regalos: " + regalos.size() + "\n");
                
                System.out.println("\n✅ Reporte generado exitosamente.");
                System.out.println("Archivo guardado como: " + nombreArchivo);
                System.out.println("\n=== VISTA PREVIA ===");
                System.out.println(String.format("%-15s %-30s %-40s %-10s",
                        "CODIGO", "NOMBRE", "DESCRIPCION", "CANTIDAD"));
                System.out.println("--------------------------------------------------------------------------------");
                for (Regalo regalo : regalos) {
                    System.out.println(String.format("%-15s %-30s %-40s %-10d",
                            regalo.getCodigo(),
                            regalo.getNombre(),
                            regalo.getDescripcion().length() > 40 ? 
                                regalo.getDescripcion().substring(0, 40) + "..." : regalo.getDescripcion(),
                            regalo.getCantidadDisponible()));
                }
                System.out.println("\nTotal de regalos: " + regalos.size());
            } catch (IOException e) {
                System.out.println("\n❌ Error al guardar el archivo: " + e.getMessage());
            }
        }
    }
}

