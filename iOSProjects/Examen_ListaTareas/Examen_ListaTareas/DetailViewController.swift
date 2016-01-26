//
//  DetailViewController.swift
//  Examen_ListaTareas
//
//  Created by Xavi Moll Villalonga on 26/01/16.
//  Copyright © 2016 Xavi Moll. All rights reserved.
//

import UIKit

class DetailViewController: UIViewController {

    @IBOutlet weak var titulotTarea: UITextField!
    @IBOutlet weak var descripcionTarea: UITextField!
    
    var titulo: String?
    var descripcion: String?
    var indexPath: NSIndexPath?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        titulotTarea.text = titulo!
        descripcionTarea.text = descripcion!
    }

    @IBAction func editarTarea(sender: UIButton) {
        listaDeTareas[indexPath!.row].titulo = titulotTarea.text!
        listaDeTareas[indexPath!.row].descripcion = descripcionTarea.text!
        dismissViewControllerAnimated(true, completion: nil)
    }
    
    @IBAction func cancelarEdicion(sender: UIButton) {
        dismissViewControllerAnimated(true, completion: nil)
    }
    
}
