//
//  ViewController.swift
//  Notas2
//
//  Created by Xavi Moll Villalonga on 02/03/16.
//  Copyright © 2016 Xavi Moll. All rights reserved.
//

import UIKit

var notas = [String]()

class ViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {

    @IBOutlet weak var tableView: UITableView!
    
    let defaults = NSUserDefaults.standardUserDefaults()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        let notasGuardadas = defaults.objectForKey("notas") as? [String]
        if let _ = notasGuardadas {
            notas = notasGuardadas!
        }
    }
    
    override func viewDidAppear(animated: Bool) {
        tableView.reloadData()
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return notas.count
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("tableViewCell", forIndexPath: indexPath)
        cell.textLabel?.text = notas[indexPath.row]
        return cell
    }
    
    func tableView(tableView: UITableView, canEditRowAtIndexPath indexPath: NSIndexPath) -> Bool {
        return true
    }
    
    func tableView(tableView: UITableView, commitEditingStyle editingStyle: UITableViewCellEditingStyle, forRowAtIndexPath indexPath: NSIndexPath) {
        if (editingStyle == UITableViewCellEditingStyle.Delete) {
            notas.removeAtIndex(indexPath.row)
            defaults.setObject(notas, forKey: "notas")
            tableView.reloadData()
        }
    }
    
    func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        performSegueWithIdentifier("EditNote", sender: self)
        tableView.deselectRowAtIndexPath(indexPath, animated: true)
    }
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        if segue.identifier == "EditNote" {
            let vc = self.storyboard?.instantiateViewControllerWithIdentifier("AddNoteViewController") as! AddNoteViewController
            let indexPath = tableView.indexPathForSelectedRow
            vc.indexPath = indexPath
            vc.editNote = notas[indexPath!.row]
            self.presentViewController(vc, animated: true, completion: nil)
        }
    }
}

