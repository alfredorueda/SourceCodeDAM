//
//  TableViewCell.swift
//  ContactosCoreData
//
//  Created by Xavi Moll Villalonga on 29/03/16.
//  Copyright © 2016 Xavi Moll. All rights reserved.
//

import UIKit

class TableViewCell: UITableViewCell {
    
    @IBOutlet weak var nombreLabel: UILabel!
    @IBOutlet weak var apellidosLabel: UILabel!
    @IBOutlet weak var telefonoLabel: UILabel!
    
    override func prepareForReuse() {
        nombreLabel.text = ""
        apellidosLabel.text = ""
        telefonoLabel.text = ""
    }
}