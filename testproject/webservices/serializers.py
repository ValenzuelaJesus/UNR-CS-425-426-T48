from rest_framework import serializers
from .models import Building, Library, HangoutSpot, Restroom, VendingMachine, Resource, Store, Dining_Option

class BuildingSerializer(serializers.ModelSerializer):
    class Meta:
        model = Building
        fields = '__all__'

class LibrarySerializer(serializers.ModelSerializer):
    class Meta:
        model = Library
        fields = '__all__'

class HangoutSpotSerializer(serializers.ModelSerializer):
    class Meta:
        model = HangoutSpot
        fields = '__all__'

class RestroomSerializer(serializers.ModelSerializer):
    class Meta:
        model = Restroom
        fields = '__all__'

class VendingMachineSerializer(serializers.ModelSerializer):
    class Meta:
        model = VendingMachine
        fields = '__all__'

class ResourceSerializer(serializers.ModelSerializer):
    class Meta:
        model = Resource
        fields = '__all__'

class StoreSerializer(serializers.ModelSerializer):
    class Meta:
        model = Store
        fields = '__all__'

class Dining_OptionSerializer(serializers.ModelSerializer):
    class Meta:
        model = Dining_Option
        fields = '__all__'

