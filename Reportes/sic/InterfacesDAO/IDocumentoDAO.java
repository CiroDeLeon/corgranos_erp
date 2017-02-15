/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sic.InterfacesDAO;

import java.util.Vector;
import sic.Dominio.Core.Documento.Asiento;
import sic.Dominio.Core.Documento.Documento;
import sic.Dominio.Core.Documento.DocumentoRep;
import sic.Dominio.Core.Documento.TipoDocumentoContable;

/**
 *
 * @author FANNY BURGOS
 */
public interface IDocumentoDAO {
 Documento PersistirDocumento(Documento documento);    
 long ObtenerIdUltimoDocumento();
 Asiento PersistirAsiento(Asiento asiento);
 int ObtenerNumeracion(String tipodocumento,int resolucion);
 int ObtenerNumeracion(String tipodocumento);
 boolean AnularDocumento(Object idDocumento,String anulador,String razon);
 boolean ModificarDocumento(Object idDocumento,Documento documento);
 boolean ModificarAsiento(Object idAsiento,Asiento asiento);
 Vector <TipoDocumentoContable> ObtenerTiposDeDocumentosContables();
 Vector <DocumentoRep> ObtenerReporteDocumento(String tipodocumento,int numeracion);
 Documento ObtenerDocumento(String tipodocumento,int numeracion);
 Documento ObtenerDocumento(Object idDocumento);
 Vector <Asiento> ObtenerAsientos(Object idDocumento);
 Documento ObtenerDocumentoDescuadrado();
 Vector<Documento> ObtenerDocumentos(String tipodocumento);
}
