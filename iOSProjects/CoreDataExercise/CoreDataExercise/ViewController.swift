//
//  ViewController.swift
//  CoreDataExercise
//
//  Created by Xavi Moll Villalonga on 29/03/16.
//  Copyright © 2016 Xavi Moll. All rights reserved.
//

import UIKit
import CoreData

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        let appDel: AppDelegate = UIApplication.sharedApplication().delegate as! AppDelegate
        //Contexto para la persistencia de datos
        let context: NSManagedObjectContext = appDel.managedObjectContext
        
        //Crear un nuevo usuario
        /*let nuevoUsuario = NSEntityDescription.insertNewObjectForEntityForName("Usuario", inManagedObjectContext: context)
        nuevoUsuario.setValue("John", forKey: "nombre")
        nuevoUsuario.setValue("5555", forKey: "password")
        
        //Insertamos el objeto en la base de datos
        do {
            try context.save()
        } catch let error {
            print(error)
        }*/
        
        
        //Consultar los datos guardados
        var request = NSFetchRequest(entityName: "Usuario")
        if let results = try? context.executeFetchRequest(request) where results.count > 0 {
            for result in results {
                print(result.valueForKey("nombre")!)
            }
        } else {
            print("No hay resultados.")
        }
        
        //Consulta de datos con condiciones y modificación
        request.predicate = NSPredicate(format: "nombre = %@", "Lola")
        if let results = try? context.executeFetchRequest(request) {
            for result in results {
                print(result.valueForKey("password")!)
                //Modificar la password
                result.setValue("nuevaPassword", forKey: "password")
            }
            //Hacemos los datos persistentes
            _ = try? context.save()
        }
        
        //Borrar datos
        request = NSFetchRequest(entityName: "Usuario")
        if let results = try? context.executeFetchRequest(request) where results.count > 0 {
            for result in results {
                if result.valueForKey("password") as! String == "1234" {
                    //Esto lo marca para borrar
                    context.deleteObject(result as! NSManagedObject)
                }
            }
            _ = try? context.save()
        }
        
    }
}

