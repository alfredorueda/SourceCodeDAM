//
//  ViewController.swift
//  ContactosCoreData
//
//  Created by Xavi Moll Villalonga on 29/03/16.
//  Copyright © 2016 Xavi Moll. All rights reserved.
//

import UIKit
import CoreData

struct Contacto {
    var nombre: String
    var apellidos: String
    var telefono: String
}

class ViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {

    @IBOutlet weak var tableView: UITableView!
    
    var appDel: AppDelegate!
    var context: NSManagedObjectContext!
    var request: NSFetchRequest!
    
    var arrayOfContacts = [Contacto]()
    
    override func viewDidAppear(animated: Bool) {
        
        appDel = UIApplication.sharedApplication().delegate as! AppDelegate
        context = appDel.managedObjectContext
        
        arrayOfContacts = [Contacto]()
        
        request = NSFetchRequest(entityName: "Contacto")
        if let results = try? context.executeFetchRequest(request) where results.count > 0 {
            for result in results {
                print(result.valueForKey("nombre")!)
                arrayOfContacts.append(Contacto.init(nombre: result.valueForKey("nombre")! as! String,
                    apellidos: result.valueForKey("apellidos")! as! String,
                    telefono: result.valueForKey("telefono")! as! String))
            }
        } else {
            print("No hay resultados.")
        }
        
        tableView.reloadData()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return arrayOfContacts.count
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("TableViewCell", forIndexPath: indexPath) as! TableViewCell
        if arrayOfContacts.count > 0 {
            cell.nombreLabel.text = arrayOfContacts[indexPath.row].nombre
            cell.apellidosLabel.text = arrayOfContacts[indexPath.row].apellidos
            cell.telefonoLabel.text = arrayOfContacts[indexPath.row].telefono
        }
        return cell
    }
    
    func tableView(tableView: UITableView, canEditRowAtIndexPath indexPath: NSIndexPath) -> Bool {
        return true
    }
    
    func tableView(tableView: UITableView, editActionsForRowAtIndexPath indexPath: NSIndexPath) -> [UITableViewRowAction]? {
        let deleteAction = UITableViewRowAction(style: .Default, title: "Borrar", handler: {(action, indexPath) -> Void in
            if let results = try? self.context.executeFetchRequest(self.request) where results.count > 0 {
                for result in results {
                    if result.valueForKey("nombre") as! String == self.arrayOfContacts[indexPath.row].nombre {
                        self.context.deleteObject(result as! NSManagedObject)
                    }
                }
                _ = try? self.context.save()
            }
            
            self.arrayOfContacts.removeAtIndex(indexPath.row)
            
            
            self.appDel = UIApplication.sharedApplication().delegate as! AppDelegate
            self.context = self.appDel.managedObjectContext
            
            self.arrayOfContacts = [Contacto]()
            
            self.request = NSFetchRequest(entityName: "Contacto")
            if let results = try? self.context.executeFetchRequest(self.request) where results.count > 0 {
                for result in results {
                    print(result.valueForKey("nombre")!)
                    self.arrayOfContacts.append(Contacto.init(nombre: result.valueForKey("nombre")! as! String,
                        apellidos: result.valueForKey("apellidos")! as! String,
                        telefono: result.valueForKey("telefono")! as! String))
                }
            } else {
                print("No hay resultados.")
            }
            
            
            tableView.reloadData()
        })
        
        return [deleteAction]
    }

}

