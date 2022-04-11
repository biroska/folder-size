package br.com.experian.foldersize;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.CRLException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.security.cert.X509Certificate;

public class ValidarCertificado {
	
	public static void main(String[] args) throws CertificateException, IOException, CRLException {
		
		String certificatePath = "C:\\temp\\karina.cer";

        CertificateFactory cf = CertificateFactory.getInstance("X509");

        X509Certificate certificate = null;
        X509CRLEntry revokedCertificate = null;
        X509CRL crl = null;
        
        FileInputStream streamCert = new FileInputStream(new File(certificatePath));

        certificate = (X509Certificate) cf.generateCertificate( streamCert );

        URL url = new URL("http://publicacao-uat.certificadodigital.com.br/repositorio/lcr/serasarfbv5_h.crl");
        URLConnection connection = url.openConnection();

        try(DataInputStream inStream = new DataInputStream(connection.getInputStream())){

            crl = (X509CRL) cf.generateCRL(inStream);
        }

        revokedCertificate = crl.getRevokedCertificate(certificate.getSerialNumber());

        if(revokedCertificate !=null){
            System.out.println("Revoked");
        }
        else{
            System.out.println("Valid");
        }
    }
}
