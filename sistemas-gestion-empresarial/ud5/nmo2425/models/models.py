# -*- coding: utf-8 -*-

from odoo import models, fields, api


class nmo2425(models.Model):
    _name = 'nmo2425.nmo2425'
    _description = 'nmo2425.nmo2425'

    name = fields.Char()
    value = fields.Integer()
    plt = fields.Many2many('nmo2425.pet', string="Pets")
    value2 = fields.Float(compute="_value_pc", store=True)
    description = fields.Text()

    @api.depends('value')
    def _value_pc(self):
        for record in self:
            record.value2 = float(record.value) / 100

