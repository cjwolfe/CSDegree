import pandas as pd
import pymysql
import getpass

mypass = getpass.getpass()

connection = pymysql.connect(host='appstate-cs3430.mysql.database.azure.com',user='wolfecj1',passwd='Coffee*Crossroads',db='wolfecj1',port=3306,ssl={'ssl_mode':'preferred'})

drop_table_command = """drop table if exists students;"""

create_table_command = """create table students(gender varchar(30),race_eth varchar(30),edu varchar(30),lunch varchar(30),test_prep varchar(30),math_score int,read_score int,writ_score int);"""

cursor.execute(drop_table_command)

if __name__ == '__main__':
    # print(repr(mypass))
    # print("Hello, world!")
