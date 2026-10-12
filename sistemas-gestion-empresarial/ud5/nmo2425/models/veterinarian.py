# -*- coding: utf-8 -*-
from odoo import models, fields


class Veterinarian(models.Model):
    _name = 'nmo2425.veterinarian'
    _description = 'Veterinarian'

    name = fields.Char(string="Veterinarian Name", required=True)
    phone = fields.Char(string="Phone")
    email = fields.Char(string="Email")
    active = fields.Boolean(string="Currently Active", default=True)
    specialization = fields.Char(string="Specialization")
    
    pet_ids = fields.One2many('nmo2425.pet', 'owner_id', string="Pets")
    employee_id = fields.Many2one('hr.employee', string="Related Employee")