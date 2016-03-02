//
//  AddNoteViewController.swift
//  Notas2
//
//  Created by Xavi Moll Villalonga on 02/03/16.
//  Copyright © 2016 Xavi Moll. All rights reserved.
//

import UIKit

class AddNoteViewController: UIViewController {

    @IBOutlet weak var textView: UITextView!
    
    let defaults = NSUserDefaults.standardUserDefaults()
    
    var editNote: String?
    var indexPath: NSIndexPath?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        if let _ = editNote {
            textView.text = editNote!
        }
    }
    
    @IBAction func addNote(sender: UIBarButtonItem) {
        if let _ = editNote {
            notas.removeAtIndex(indexPath!.row)
        }
        notas.append(textView.text!)
        defaults.setObject(notas, forKey: "notas")
        dismissViewControllerAnimated(true, completion: nil)
    }
    
    @IBAction func cancelAddingNote(sender: UIBarButtonItem) {
        dismissViewControllerAnimated(true, completion: nil)
    }
    
}
