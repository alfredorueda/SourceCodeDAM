//
//  CeldaPersonalizadaTableViewCell.swift
//  Examen_ListaTareas
//
//  Created by Xavi Moll Villalonga on 26/01/16.
//  Copyright © 2016 Xavi Moll. All rights reserved.
//

import UIKit

class CeldaPersonalizadaTableViewCell: UITableViewCell {
    
    @IBOutlet weak var imagenTarea: UIImageView!
    @IBOutlet weak var imagenCheckbox: UIImageView!
    @IBOutlet weak var tituloTarea: UILabel!
    @IBOutlet weak var descripcionTarea: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
    override init(style: UITableViewCellStyle, reuseIdentifier: String?){
        super.init(style: style, reuseIdentifier: reuseIdentifier)
    }
    
    required init(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)!
    }

}
