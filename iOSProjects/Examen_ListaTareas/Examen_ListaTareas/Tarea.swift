//
//  Tarea.swift
//  Examen_ListaTareas
//
//  Created by Xavi Moll Villalonga on 26/01/16.
//  Copyright © 2016 Xavi Moll. All rights reserved.
//

import UIKit

var listaDeTareas = [Tarea]()

class Tarea: NSObject {

    var titulo: String?
    var descripcion: String?
    var realizada : Bool?
    var imagen : UIImage?
    
    init(titulo: String, descripcion: String) {
        self.titulo = titulo
        self.descripcion = descripcion
        self.realizada = false
        self.imagen = nil
    }
}
