from django.urls import path
from .views import *

urlpatterns = [
    path('Building/', buildingList.as_view()),
    path('buildings/<int:pk>/', buildingDetail.as_view(), name = 'building-detail'),
    path('libraries/', libraryList.as_view(), name='library-list'),
    path('libraries/<int:pk>/', libraryDetail.as_view(), name='library-detail'),
    path('hangoutspots/', hangoutspotsList.as_view(), name='hangoutspots-list'),
    path('hangoutspots/<int:pk>/', hangoutspotsDetail.as_view(), name='hangoutspots-detail'),
    path('restrooms/', restroomList.as_view(), name='restroom-list'),
    path('restrooms/<int:pk>/', restroomDetail.as_view(), name='hangoutspots-detail'),
    path('vendingmachines/', vendingmachineList.as_view(), name='vendingmachine-list'),
    path('vendingmachines/<int:pk>/', vendingmachineDetail.as_view(), name='vendingmachine-detail'),
    path('resources/', resourceList.as_view(), name='resource-list'),
    path('resources/<int:pk>/', resourceDetail.as_view(), name='resource-detail'),
    path('store/', storeList.as_view(), name='store-list'),
    path('store/<int:pk>/', storeDetail.as_view(), name='store-detail'),
    path('dining_option/', dining_optionList.as_view(), name='dining_option-list'),
    path('dining_option/<int:pk>/', dining_optionDetail.as_view(), name='dining_option-detail'),
    path('labs/', labList.as_view(), name='lab-list'),
    path('labs/<int:pk>/', labDetail.as_view(), name='lab-detail'),
    path('elevators/', elevatorList.as_view(), name='elevator-list'),
    path('elevators/<int:pk>/', elevatorDetail.as_view(), name='elevator-detail'),
    ]
