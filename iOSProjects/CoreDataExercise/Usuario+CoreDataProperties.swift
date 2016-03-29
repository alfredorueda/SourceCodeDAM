//
//  Usuario+CoreDataProperties.swift
//  CoreDataExercise
//
//  Created by Xavi Moll Villalonga on 29/03/16.
//  Copyright © 2016 Xavi Moll. All rights reserved.
//
//  Choose "Create NSManagedObject Subclass…" from the Core Data editor menu
//  to delete and recreate this implementation file for your updated model.
//

import Foundation
import CoreData

extension Usuario {

    @NSManaged var nombre: String?
    @NSManaged var password: String?

}
