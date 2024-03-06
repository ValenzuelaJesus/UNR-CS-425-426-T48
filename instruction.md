### Instructions for changes made to the Google cloud database

Changed firewall settings to allow HTTP and HTTPS traffic

# log into the vm using the SSH terminal

# Login to super user
>sudo su

# Update
>apt-get update

# Install git
>apt-get install git

# Clone from the git
>git clone https://github.com/ValenzuelaJesus/UNR-CS-425-426-T48.git

# Check to see if previous command worked
>ls
# Should return something like this
>root@unrteam48arudatabase:/# ls
>UNR-CS-425-426-T48  bin  boot  dev  etc  home  lib  lib32  lib64  libx32  lost+found  media  mnt  opt  proc  root  run  sbin  snap  srv  sys  tmp  usr  var

>cd UNR-CS-425-426-T48

>root@unrteam48arudatabase:/UNR-CS-425-426-T48# ls
>README.md

# Pull from webservice branch and check 
>git pull origin Webservice

>root@unrteam48arudatabase:/UNR-CS-425-426-T48# ls
>README.md  testproject

# Install requirements 
>apt install python3-pip python3-dev libpq-dev postgresql-contrib nginx curl

# Update pip
>pip3 install --upgrade pip

# Install virtualenv
>pip3 install virtualenv

# Connect to postgres psql
>sudo -u postgres psql

# Create a user and give relevant permissions
>CREATE USER webser WITH PASSWORD '******';
>ALTER ROLE webser SET client_encoding TO 'utf8';
>ALTER ROLE webser SET default_transaction_isolation TO 'read committed';
>ALTER ROLE webser SET timezone TO 'UTC';
>GRANT ALL PRIVILEGES ON DATABASE cs_426_database to webser;

# Exit psql
>\q
# Login as super user
>sudo su

# Manuever to git folder use ls to check and make sure similar
>root@unrteam48arudatabase:/# cd UNR-CS-425-426-T48
>root@unrteam48arudatabase:/UNR-CS-425-426-T48# ls
>README.md  testproject

# Create a venv at this location double check with ls
>virtualenv venv
>root@unrteam48arudatabase:/UNR-CS-425-426-T48# ls
>README.md  testproject  venv

# Activate Venv
>source venv/bin/activate

# If successful terminal will contain (venv) 
>(venv) root@unrteam48arudatabase:/UNR-CS-425-426-T48# 
