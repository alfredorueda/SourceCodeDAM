//
//  PreguntasRespuestas.swift
//  PreguntasYRespuestas
//
//  Created by Xavi Moll Villalonga on 27/10/15.
//  Copyright © 2015 Xavi Moll Villalonga. All rights reserved.
//

import Foundation

class PreguntasRespuestas {
    var enunciado: String
    var respuestaOK: String
    var respuestaKO1: String
    var respuestaKO2: String
    var respuestaKO3: String
    
    init(enunciado: String, respuestaOK: String, respuestaKO1: String, respuestaKO2: String, respuestaKO3: String){
        self.enunciado = enunciado
        self.respuestaOK = respuestaOK
        self.respuestaKO1 = respuestaKO1
        self.respuestaKO2 = respuestaKO2
        self.respuestaKO3 = respuestaKO3
    }
    
}