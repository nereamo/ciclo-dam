# -*- coding: utf-8 -*-
from odoo import models, fields, api


class Pet(models.Model):
    _name = 'nmo2425.pet'
    _description = 'Pets of the veterinary clinic'

    name = fields.Char(string="Pet Name", required=True, help="Name of the pet")
    age = fields.Integer(string="Age")
    type = fields.Selection([
        ('dog', 'Dog'),
        ('cat', 'Cat'),
        ('bird', 'Bird'),
        ('other', 'Other')
    ], string="Pet Type", help="Select the type of pet")
    vaccinated = fields.Boolean(string="Vaccinated", default=False)
    weight = fields.Float(string="Weight (kg)")
    description = fields.Text(string="Additional Notes")

    owner_id = fields.Many2one('nmo2425.veterinarian', string="Veterinarian")
    appointment_ids = fields.One2many('nmo2425.appointment', 'pet_id', string="Appointments")

    @api.depends('weight')
    def _compute_health_status(self):
        for pet in self:
            pet.health_status = 'Normal' if pet.weight >= 2 else 'Underweight'

    health_status = fields.Char(string="Health Status", compute="_compute_health_status", store=True)