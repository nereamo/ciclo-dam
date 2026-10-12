# -*- coding: utf-8 -*-

from odoo import models, fields


class Appointment(models.Model):
    _name = 'nmo2425.appointment'
    _inherit = ['mail.thread', 'mail.activity.mixin']
    _description = 'Pet Appointment'

    date = fields.Date(string="Date", required=True)
    notes = fields.Text(string="Appointment Notes")
    additional_info = fields.Text(string='Additional Info')
    cost = fields.Float(string="Cost")
    is_follow_up = fields.Boolean(string="Follow-up", default=False) #Cita de seguimiento

    pet_id = fields.Many2one('nmo2425.pet', string="Pet", required=True)
    veterinarian_id = fields.Many2one('nmo2425.veterinarian', string="Veterinarian", required=True)