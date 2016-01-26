//
//  FirstViewController.swift
//  Examen_ListaTareas
//
//  Created by Xavi Moll Villalonga on 26/01/16.
//  Copyright © 2016 Xavi Moll. All rights reserved.
//

import UIKit

class FirstViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var tableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    override func viewDidAppear(animated: Bool) {
        tableView.reloadData()
    }

    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return listaDeTareas.count
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("celdaPersonalizada") as! CeldaPersonalizadaTableViewCell
        cell.tituloTarea.text = listaDeTareas[indexPath.row].titulo!
        cell.descripcionTarea.text = listaDeTareas[indexPath.row].descripcion!
        cell.imagenTarea.image = UIImage(named: "LeftImage")
        if let realizada = listaDeTareas[indexPath.row].realizada {
            if realizada {
                cell.imagenCheckbox.image = UIImage(named: "done")
            } else {
                cell.imagenCheckbox.image = nil
            }
        }
        return cell
    }
    
    func tableView(tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        return "Examen Xavi Moll"
    }
    
    func tableView(tableView: UITableView, editActionsForRowAtIndexPath indexPath: NSIndexPath) -> [UITableViewRowAction]? {
        let deleteAction = UITableViewRowAction(style: .Default, title: "Borrar", handler: {(action, indexPath) -> Void in
            listaDeTareas.removeAtIndex(indexPath.row)
            tableView.reloadData()
            })
        
        let doneAction = UITableViewRowAction(style: .Normal, title: "Hecha", handler: {(action, indexPath) -> Void in
            listaDeTareas[indexPath.row].realizada! = true
            tableView.reloadData()
            })
        
        return [deleteAction, doneAction]
    }
    
    func tableView(tableView: UITableView, commitEditingStyle editingStyle: UITableViewCellEditingStyle, forRowAtIndexPath indexPath: NSIndexPath) {
        
    }
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        let vc = segue.destinationViewController as! DetailViewController
        let tareaIndex = tableView.indexPathForSelectedRow
        let tareaSeleccionada = listaDeTareas[tareaIndex!.row]
        
        vc.titulo = tareaSeleccionada.titulo!
        vc.descripcion = tareaSeleccionada.descripcion!
        vc.indexPath = tareaIndex
    }

}

