//
//  AddContactViewController.swift
//  ContactosCoreData
//
//  Created by Xavi Moll Villalonga on 29/03/16.
//  Copyright © 2016 Xavi Moll. All rights reserved.
//

import UIKit
import CoreData

class AddContactViewController: UIViewController {

    @IBOutlet weak var nombreTextField: UITextField!
    @IBOutlet weak var apellidosTextField: UITextField!
    @IBOutlet weak var telefonoTextField: UITextField!
    
    var appDel: AppDelegate!
    var context: NSManagedObjectContext!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        appDel = UIApplication.sharedApplication().delegate as! AppDelegate
        context = appDel.managedObjectContext
    }
    
    @IBAction func cancelAddingContact(sender: UIBarButtonItem) {
        dismissViewControllerAnimated(true, completion: nil)
    }
    
    
    @IBAction func addContact(sender: UIBarButtonItem) {
        let nuevoUsuario = NSEntityDescription.insertNewObjectForEntityForName("Contacto", inManagedObjectContext: context)
        nuevoUsuario.setValue("\(nombreTextField.text!)", forKey: "nombre")
        nuevoUsuario.setValue("\(apellidosTextField.text!)", forKey: "apellidos")
        nuevoUsuario.setValue("\(telefonoTextField.text!)", forKey: "telefono")
        
        //Insertamos el objeto en la base de datos
        do {
            try context.save()
        } catch let error {
            print(error)
        }
        dismissViewControllerAnimated(true, completion: nil)
    }
    

}
