import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:image_picker/image_picker.dart';
import 'package:productes_app/providers/product_form_provider.dart';
import 'package:productes_app/services/products_service.dart';
import 'package:productes_app/ui/input_decorations.dart';
import 'package:productes_app/widgets/widgets.dart';
import 'package:provider/provider.dart';

//Clase que muestra la interfaz de un producto
class ProductScreen extends StatelessWidget {
  const ProductScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {

    final productService = Provider.of<ProductsService>(context);

    /*
    El proveedor notificará a __ProductScreenBody sobre los cambios 
    realizados en ProductFormProvider, asi la interfaz se actualizará 
    */
    return ChangeNotifierProvider(create: ( _ ) => ProductFormProvider(productService.selectedProduct),
      child: _ProductScreenBody(productService: productService)
    );
  }
}


//Extrae scaffold dentro del widgetBody
class _ProductScreenBody extends StatelessWidget {
  const _ProductScreenBody({
    Key? key,
    required this.productService,
  }) : super(key: key);

  final ProductsService productService;

  //Interfaz que muestra los detalles de un producto
  @override
  Widget build(BuildContext context) {

    final productForm = Provider.of<ProductFormProvider>(context);

    return Scaffold(
      body: SingleChildScrollView(
        child: Column(
          children: [
            Stack(
              children: [
                ProductImage(url:productService.selectedProduct.picture), //Llamar al provider para retornar la imagen
                Positioned(
                  top: 60,
                  left: 20,
                  child: IconButton(
                    onPressed: () => Navigator.of(context).pop(),
                    icon: Icon(
                      Icons.arrow_back_ios_new,
                      size: 30,
                      color: Colors.black,
                    ),
                  ),
                ),
                Positioned(
                  top: 60,
                  right: 20,
                  child: IconButton(
                    onPressed: () async {
                      final ImagePicker _picker = ImagePicker();
                      //final XFile? image = await _picker.pickImage(source: ImageSource.gallery);
                      final XFile? photo = await _picker.pickImage(source: ImageSource.camera);
                      print(photo!.path);

                      productService.updateSelectedImage(photo.path);
                    },
                    icon: Icon(
                      Icons.camera_alt_outlined,
                      size: 30,
                      color: Colors.black,
                    ),
                  ),
                ),
              ],
            ),
            _ProductForm(),
            SizedBox(
              height: 100,
            )
          ],
        ),
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.endDocked,
      floatingActionButton: FloatingActionButton(
          backgroundColor: Colors.black,
          child: productService.isSaving
            ? CircularProgressIndicator(color: Colors.white)
            : Icon(Icons.save_outlined, color: Colors.white),
          onPressed: productService.isSaving
            ? null
            : () async {
              if(!productForm.isValidForm()) return;//Llamada al método que valida el formulario.
              final String? imageUrl = await productService.uploadImage();
              if(imageUrl != null) productForm.tempProduct.picture = imageUrl;
              productService.saveOrCreateProduct(productForm.tempProduct);
            }
        ),
      );
  }
  
}

//Formulario para introducir los detalles del producto
class _ProductForm extends StatelessWidget {
  @override
  Widget build(BuildContext context) {

    final productForm = Provider.of<ProductFormProvider>(context);
    final tempProduct = productForm.tempProduct;

    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 10),
      child: Container(
        padding: EdgeInsets.symmetric(horizontal: 20),
        width: double.infinity,
        decoration: _buildBoxDecoration(),
        child: Form(
          key: productForm.formKey, //Asociar el formulario con la key de product_form_provider
          autovalidateMode: AutovalidateMode.onUserInteraction, //Oculta el mensaje 'Error' al escribir en un campo vacío
          child: Column(
            children: [
              SizedBox(height: 10),
              TextFormField(
                initialValue: tempProduct.name,
                onChanged: (value) => tempProduct.name = value,
                validator: (value) { //Validar que se ha introducido un nuevo valor antes de guardar
                  if( value == null || value.length < 1)
                  return 'El nombre no puede estar vacío';
                },
                decoration: InputDecorations.authInputDecoration(
                    hintText: 'Nombre del producto', labelText: 'Nombre:'),
              ),
              SizedBox(height: 30),
              TextFormField(
                initialValue: '${tempProduct.price}', //Convierte el valor de double a String
                inputFormatters: [
                  FilteringTextInputFormatter.allow(
                    RegExp(r'^(\d+)?\.?\d{0,2}') //Establece norma: despúes del punto solo dos decimales
                  ),
                ],
                onChanged: (value){ //Si el valor no puede parsearse, devuelve 0, sino establece el precio correcto
                  if(double.tryParse(value) == null){
                    tempProduct.price = 0;
                  }else{
                    tempProduct.price = double.parse(value);
                  }
                },
                validator: (value) { //Validar que se ha introducido un nuevo valor antes de guardar
                  if( value == null || value.length < 1)
                  return 'El precio es obligatório';
                },
                keyboardType: TextInputType.number,
                decoration: InputDecorations.authInputDecoration(
                    hintText: ' ', labelText: 'Precio:'),
              ),
              SizedBox(height: 30),
              SwitchListTile.adaptive(
                value: tempProduct.available,
                title: Text('Disponible'),
                activeColor: Colors.black,
                onChanged: (value) => productForm.updateAvailability(value) //Llamada al método de product_form_provider
              ),
              SizedBox(height: 30),
            ],
          ),
        ),
      ),
    );
  }

  //Marco donde se muestra la imagen
  BoxDecoration _buildBoxDecoration() => BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.only(
          bottomRight: Radius.circular(25),
          bottomLeft: Radius.circular(25),
        ),
        boxShadow: [
          BoxShadow(
              color: Colors.black.withOpacity(0.05),
              offset: Offset(0, 5),
              blurRadius: 5),
        ],
      );
}
