/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sic.InterfacesDAO;

import java.util.Vector;
import sic.Dominio.Servicios.Dian.RetencionDian;

/**
 *
 * @author FANNY BURGOS
 */
public interface IDianDAO {
   Vector <RetencionDian> ObtenerTablaDeRetenciones(int año);
   RetencionDian AgregarRegistroEnTablaDeRetenciones(RetencionDian rd);
   RetencionDian ModificarRegistroEnTablaDeRetenciones(Object id,RetencionDian rd);
   RetencionDian ObtenerRetencionDian(Object idAuxiliar);
}
