from django.shortcuts import render
from .models import *
from rest_framework import generics
from .serializers import *

# Create your views here.
class buildingList(generics.ListAPIView): #Returns a list of all buildings
    queryset = building.objects.all()
    serializer_class = buildingSerializer
    print(queryset)

class buildingDetail(generics.RetrieveUpdateDestroyAPIView): #Returns a single Building Object by primary key
    queryset = building.objects.all()
    serializer_class = buildingSerializer

class libraryList(generics.ListAPIView): 
    queryset = library.objects.all()
    serializer_class = librarySerializer

class libraryDetail(generics.RetrieveUpdateDestroyAPIView): 
    queryset = library.objects.all()
    serializer_class = librarySerializer

class hangoutspotsList(generics.ListAPIView): 
    queryset = hangoutspots.objects.all()
    serializer_class = hangoutspotsSerializer

class hangoutspotsDetail(generics.RetrieveUpdateDestroyAPIView): 
    queryset = hangoutspots.objects.all()
    serializer_class = hangoutspotsSerializer

class restroomList(generics.ListAPIView):
    queryset = restroom.objects.all()
    serializer_class = restroomSerializer

class restroomDetail(generics.RetrieveUpdateDestroyAPIView): 
    queryset = restroom.objects.all()
    serializer_class = restroomSerializer

class vendingmachineList(generics.ListAPIView): 
    queryset = vendingmachine.objects.all()
    serializer_class = vendingmachineSerializer

class vendingmachineDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = vendingmachine.objects.all()
    serializer_class = vendingmachineSerializer

class resourceList(generics.ListAPIView): 
    queryset = resource.objects.all()
    serializer_class = resourceSerializer

class resourceDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = resource.objects.all()
    serializer_class = resourceSerializer

class storeList(generics.ListAPIView): 
    queryset = store.objects.all()
    serializer_class = storeSerializer

class storeDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = store.objects.all()
    serializer_class = storeSerializer

class dining_optionList(generics.ListAPIView): 
    queryset = dining_option.objects.all()
    serializer_class = dining_optionSerializer

class dining_optionDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = dining_option.objects.all()
    serializer_class = dining_optionSerializer

class labList(generics.ListAPIView):
    queryset = lab.objects.all()
    serializer_class = labSerializer

class labDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = lab.objects.all()
    serializer_class = labSerializer

class elevatorList(generics.ListAPIView):
    queryset = elevator.objects.all()
    serializer_class = elevatorSerializer

class elevatorDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = elevator.objects.all()
    serializer_class = elevatorSerializer

class staircaseList(generics.ListAPIView):
    queryset = staircase.objects.all()
    serializer_class = staircaseSerializer

class staircaseDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = staircase.objects.all()
    serializer_class = staircaseSerializer

class special_featureList(generics.ListAPIView):
    queryset = special_feature.objects.all()
    serializer_class = special_featureSerializer

class special_featureDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = special_feature.objects.all()
    serializer_class = special_featureSerializer
