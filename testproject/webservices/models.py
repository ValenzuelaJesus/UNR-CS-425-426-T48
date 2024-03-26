from django.db import models
from django.contrib.gis.db import models

# Create your models here.
class Building(models.Model):
    gps_coords = models.PointField(blank = True, null = True, srid=4326)
    building_code = models.CharField(max_length=10, unique=True)
    building_name = models.CharField(max_length=255)
    building_num = models.CharField(max_length=10)
    department = models.CharField(max_length=255)
    operating_hours = models.CharField(max_length=255)

class Library(models.Model):
    building = models.ForeignKey(Building, on_delete=models.CASCADE)
    location = models.CharField(max_length=255)
    description = models.CharField(max_length=255)

class HangoutSpot(models.Model):
    building = models.ForeignKey(Building, on_delete=models.CASCADE)
    location = models.CharField(max_length=255)
    description = models.CharField(max_length=255)
    outlet = models.BooleanField(default=False)

class Restroom(models.Model):
    building = models.ForeignKey(Building, on_delete=models.CASCADE)
    location = models.CharField(max_length=255)
    accessibility = models.BooleanField(default=False)

class VendingMachine(models.Model):
    building = models.ForeignKey(Building, on_delete=models.CASCADE)
    location = models.CharField(max_length=255)
    type = models.CharField(max_length=20)

class Resource(models.Model):
    building = models.ForeignKey(Building, on_delete=models.CASCADE)
    description = models.CharField(max_length=255)
    location = models.CharField(max_length=255)
    operating_hours = models.CharField(max_length=50)
    weblink = models.CharField(max_length=255)

class Store(models.Model):
    building = models.ForeignKey(Building, on_delete=models.CASCADE)
    description = models.CharField(max_length=255)
    location = models.CharField(max_length=255)
    operating_hours = models.CharField(max_length=50)

class Dining_Option(models.Model):
    building = models.ForeignKey(Building, on_delete = models.CASCADE)
    description = models.CharField(max_length=255)
    location = models.CharField(max_length=255)
    operating_hours = models.CharField(max_length=50)
    weblink = models.CharField(max_length=255)
    menulink = models.CharField(max_length=255)

class Lab(models.Model):
    building = models.ForeignKey(Building, on_delete=models.CASCADE)
    location = models.CharField(max_length=255)
    type = models.CharField(max_length=20)
    department = models.CharField(max_length=255)

class Elevator(models.Model):
    building = models.ForeignKey(Building, on_delete = models.CASCADE)
    location = models.CharField(max_length=255)

class Staircase(models.Model):
    building = models.ForeignKey(Building, on_delete = models.CASCADE)
    location = models.CharField(max_length=255)

class Special_Feature(models.Model):
    building = models.ForeignKey(Building, on_delete = models.CASCADE)
    location = models.CharField(max_length=255)
    description = models.CharField(max_length=255)
