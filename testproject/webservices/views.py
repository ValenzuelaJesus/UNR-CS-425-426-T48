from django.shortcuts import render
from .models import *
from rest_framework import generics
from .serializers import *

# Create your views here.
class BuildingList(generics.ListCreateAPIView): #Returns a list of all buildings
    queryset = Building.objects.all()
    serializer_class = BuildingSerializer

class BuildingDetail(generics.RetrieveUpdateDestroyAPIView): #Returns a single Building Object by primary key
    queryset = Building.objects.all()
    serializer_class = BuildingSerializer

class LibraryList(generics.ListCreateAPIView): 
    queryset = Library.objects.all()
    serializer_class = LibrarySerializer

class LibraryDetail(generics.RetrieveUpdateDestroyAPIView): 
    queryset = Library.objects.all()
    serializer_class = LibrarySerializer

class HangoutSpotList(generics.ListCreateAPIView): 
    queryset = HangoutSpot.objects.all()
    serializer_class = HangoutSpotSerializer

class HangoutSpotDetail(generics.RetrieveUpdateDestroyAPIView): 
    queryset = HangoutSpot.objects.all()
    serializer_class = HangoutSpotSerializer

class RestroomList(generics.ListCreateAPIView):
    queryset = Restroom.objects.all()
    serializer_class = RestroomSerializer

class RestroomDetail(generics.RetrieveUpdateDestroyAPIView): 
    queryset = Restroom.objects.all()
    serializer_class = RestroomSerializer

class VendingMachineList(generics.ListCreateAPIView): 
    queryset = VendingMachine.objects.all()
    serializer_class = VendingMachineSerializer

class VendingMachineDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = VendingMachine.objects.all()
    serializer_class = VendingMachineSerializer

class ResourceList(generics.ListCreateAPIView): 
    queryset = Resource.objects.all()
    serializer_class = ResourceSerializer

class ResourceDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = Resource.objects.all()
    serializer_class = ResourceSerializer

class StoreList(generics.ListCreateAPIView): 
    queryset = Resource.objects.all()
    serializer_class = StoreSerializer

class StoreDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = Store.objects.all()
    serializer_class = StoreSerializer

class Dining_OptionList(generics.ListCreateAPIView): 
    queryset = Dining_Option.objects.all()
    serializer_class = Dining_OptionSerializer

class Dining_OptionDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = Dining_Option.objects.all()
    serializer_class = Dining_OptionSerializer
