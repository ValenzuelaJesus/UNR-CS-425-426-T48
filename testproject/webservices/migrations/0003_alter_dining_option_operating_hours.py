# Generated by Django 4.2.11 on 2024-04-04 00:43

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('webservices', '0002_rename_hangoutspot_hangoutspots'),
    ]

    operations = [
        migrations.AlterField(
            model_name='dining_option',
            name='operating_hours',
            field=models.CharField(max_length=255),
        ),
    ]