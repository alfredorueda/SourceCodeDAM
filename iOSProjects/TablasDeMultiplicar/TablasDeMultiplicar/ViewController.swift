//
//  ViewController.swift
//  TablasDeMultiplicar
//
//  Created by Xavi Moll Villalonga on 01/03/16.
//  Copyright © 2016 Xavi Moll. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var sliderNumbers: UISlider!
    @IBOutlet weak var tableView: UITableView!
    
    var sliderValue: Int!

    override func viewDidLoad() {
        super.viewDidLoad()
        sliderValue = Int(sliderNumbers.value)
    }
    
    @IBAction func sliderChanged(sender: UISlider) {
        sliderValue = Int(sender.value)
        tableView.reloadData()
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 11
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("tableViewCell", forIndexPath: indexPath)
        cell.textLabel?.text = "\(indexPath.row) x \(sliderValue) = \(indexPath.row*sliderValue)"
        return cell
    }
    
}

