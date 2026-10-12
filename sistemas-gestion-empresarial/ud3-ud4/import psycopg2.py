import psycopg2
import pandas as pd
import matplotlib.pyplot as plt

# conexión
conn = psycopg2.connect(host="192.168.1.74",database="SGE",user="nerea",password="nerea")

# create a cursor
cur = conn.cursor()

# execute a statement
print('\nPostgreSQL database version:')
cur.execute('SELECT version()')
db_version = cur.fetchall()
print(db_version)


print('\nTabla lunch_product: ')
cur.execute('SELECT * FROM lunch_product')
productos = cur.fetchall()
productos[0:26]# rows de la columna
print(productos[3]) # muestra el titulo de la columna

# Column names
print('\nColumn name: ')
cur.execute("SELECT column_name FROM information_schema.columns WHERE table_name = 'lunch_product'")
labels = cur.fetchall()
labels[0:26] # rows de la columna
print(labels[7])# muestra el titulo de la columna


print('\nQuitar comillas: ')
newlab = [label[0] for label in labels]
print(newlab)


# Uso de pandas para añadir los datos en dataframe
print('\nTable products: ')
df =[]
df = pd.DataFrame(productos, columns=newlab)
print(df.head())


# Tipos de datos, cambiar price a float
print('\nTable "price = object": ')
print(df.dtypes)


print('\nTable "price = float": ')
df['price'] = df['price'].astype(float)
print(df.dtypes) # verificar el cambio de tipo


# Histograma
print('Histograma: ')
plt.figure(figsize=[15,8])
plt.title('Histograma de precios de productos - lunch_products') 
plt.xlabel('Precio')
plt.ylabel('Numero de productos')
plt.hist(df['price'], bins=100, facecolor='blue', alpha=0.5, edgecolor = 'black')
plt.savefig("histograma_precios_de_productos_lunchProducts.png",dpi=200)
plt.show()


# Cerrar conexión 
conn.close()