from django.urls import path
from .views import *

urlpatterns = [
    path('buildings/', BuildingList.as_view()),
    path('buildings/<int:pk>/', BuildingDetail.as_view()),
    path('libraries/', LibraryList.as_view()),
    path('libraries/<int:pk>/', LibraryDetail.as_view()),
    path('hangoutspots/', HangoutSpotList.as_view()),
    path('hangoutspots/<int:pk>/', HangoutSpotDetail.as_view()),
    path('restrooms/', RestroomList.as_view()),
    path('restrooms/<int:pk>/', RestroomDetail.as_view()),
    path('vendingmachines/', VendingMachineList.as_view()),
    path('vendingmachines/<int:pk>/', VendingMachineDetail.as_view()),
    path('resources/', ResourceList.as_view()),
    path('resources/<int:pk>/', ResourceDetail.as_view()),
    path('store/', StoreList.as_view()),
    path('store/<int:pk>/', StoreDetail.as_view()),
    path('dining_option/', Dining_OptionList.as_view()),
    path('dining_option/<int:pk>/', Dining_OptionDetail.as_view()),
    ]
