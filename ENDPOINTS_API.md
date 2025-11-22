# 📚 API REST - Endpoints Completos

## ✅ Endpoints Creados

He creado **controladores REST completos** para todas las entidades. Aquí está la lista completa de endpoints disponibles:

---

## 📦 Categorías
**Base URL:** `/api/categorias`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/categorias` | Listar todas las categorías |
| GET | `/api/categorias/{id}` | Obtener una categoría por ID |
| POST | `/api/categorias` | Crear nueva categoría |
| PUT | `/api/categorias/{id}` | Actualizar categoría |
| DELETE | `/api/categorias/{id}` | Eliminar categoría |

**Ejemplo POST:**
```json
{
  "nombre": "Nueva Categoría"
}
```

---

## 🏷️ Marcas
**Base URL:** `/api/marcas`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/marcas` | Listar todas las marcas |
| GET | `/api/marcas/{id}` | Obtener una marca por ID |
| POST | `/api/marcas` | Crear nueva marca |
| PUT | `/api/marcas/{id}` | Actualizar marca |
| DELETE | `/api/marcas/{id}` | Eliminar marca |

**Ejemplo POST:**
```json
{
  "nombre": "Nike",
  "descripcion": "Ropa deportiva"
}
```

---

## 📏 Unidades de Medida
**Base URL:** `/api/unidades-medida`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/unidades-medida` | Listar todas las unidades |
| GET | `/api/unidades-medida/{id}` | Obtener una unidad por ID |
| POST | `/api/unidades-medida` | Crear nueva unidad |
| PUT | `/api/unidades-medida/{id}` | Actualizar unidad |
| DELETE | `/api/unidades-medida/{id}` | Eliminar unidad |

**Ejemplo POST:**
```json
{
  "nombre": "Gramo",
  "abreviatura": "g"
}
```

---

## 📦 Presentaciones
**Base URL:** `/api/presentaciones`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/presentaciones` | Listar todas las presentaciones |
| GET | `/api/presentaciones/{id}` | Obtener una presentación por ID |
| POST | `/api/presentaciones` | Crear nueva presentación |
| PUT | `/api/presentaciones/{id}` | Actualizar presentación |
| DELETE | `/api/presentaciones/{id}` | Eliminar presentación |

**Ejemplo POST:**
```json
{
  "nombre": "Lata",
  "descripcion": "Envase metálico"
}
```

---

## 👥 Clientes
**Base URL:** `/api/clientes`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/clientes` | Listar todos los clientes |
| GET | `/api/clientes/{id}` | Obtener un cliente por ID |
| POST | `/api/clientes` | Crear nuevo cliente |
| PUT | `/api/clientes/{id}` | Actualizar cliente |
| DELETE | `/api/clientes/{id}` | Eliminar cliente |

**Ejemplo POST:**
```json
{
  "nombre": "María",
  "apellidos": "García López",
  "nroDocumento": "12345678",
  "direccion": "Av. Principal 123",
  "celular": "987654321",
  "estado": true
}
```

---

## 📦 Productos
**Base URL:** `/api/productos`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/productos` | Listar todos los productos ✅ PROBADO |
| POST | `/api/productos` | Crear nuevo producto |

**Ejemplo POST:**
```json
{
  "nombre": "Nuevo Producto",
  "categoria": {"idCategoria": 1},
  "marca": {"idMarca": 1},
  "unidadMedida": {"idUnidad": 1},
  "presentacion": {"idPresentacion": 1},
  "estado": true
}
```

---

## 📦 Lotes (Inventario)
**Base URL:** `/api/lotes`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/lotes` | Listar todos los lotes |
| GET | `/api/lotes/{id}` | Obtener un lote por ID |
| POST | `/api/lotes` | Crear nuevo lote |
| PUT | `/api/lotes/{id}` | Actualizar lote |
| DELETE | `/api/lotes/{id}` | Eliminar lote |

**Ejemplo POST:**
```json
{
  "producto": {"idProducto": 1},
  "codigoLote": "L001",
  "precioCompra": 10.50,
  "precioVenta": 15.00,
  "fechaVencimiento": "2025-12-31",
  "cantidad": 100
}
```

---

## 🧾 Comprobantes (Solo Consulta)
**Base URL:** `/api/comprobantes`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/comprobantes` | Listar todos los comprobantes |
| GET | `/api/comprobantes/{id}` | Obtener un comprobante por ID |

> **Nota:** Los comprobantes se crean automáticamente a través del endpoint `/api/ventas`

---

## 💰 Ventas (FIFO)
**Base URL:** `/api/ventas`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/ventas` | Registrar nueva venta ✅ PROBADO |

**Ejemplo POST:**
```json
{
  "idCliente": 1,
  "idTipoComprobante": 2,
  "serie": "B001",
  "productos": [
    {
      "idProducto": 1,
      "cantidad": 5
    },
    {
      "idProducto": 2,
      "cantidad": 10
    }
  ]
}
```

**Características:**
- ✅ Lógica FIFO automática
- ✅ Numeración correlativa automática
- ✅ Actualización de stock automática
- ✅ Validación de disponibilidad

---

## 🧪 Cómo Probar los Endpoints

### Opción 1: Navegador (solo GET)
```
http://localhost:8080/api/categorias
http://localhost:8080/api/marcas
http://localhost:8080/api/productos
http://localhost:8080/api/clientes
http://localhost:8080/api/lotes
http://localhost:8080/api/comprobantes
```

### Opción 2: Postman (Recomendado)
1. Abre Postman
2. Selecciona el método (GET, POST, PUT, DELETE)
3. Ingresa la URL completa
4. Para POST/PUT: agrega el JSON en Body > raw > JSON

### Opción 3: PowerShell
```powershell
# Listar categorías
Invoke-WebRequest -Uri "http://localhost:8080/api/categorias" | Select-Object -ExpandProperty Content

# Crear categoría
$data = @{nombre = "Nueva Categoría"} | ConvertTo-Json
Invoke-WebRequest -Uri "http://localhost:8080/api/categorias" -Method POST -ContentType "application/json" -Body $data
```

---

## 📊 Resumen de Archivos Creados

### Controladores (9)
- ✅ `CategoriaController.java`
- ✅ `MarcaController.java`
- ✅ `UnidadMedidaController.java`
- ✅ `PresentacionController.java`
- ✅ `ClienteController.java`
- ✅ `ProductoController.java`
- ✅ `LoteController.java`
- ✅ `ComprobanteController.java`
- ✅ `VentaController.java`

### Repositorios (9)
- ✅ `CategoriaRepository.java`
- ✅ `MarcaRepository.java`
- ✅ `UnidadMedidaRepository.java`
- ✅ `PresentacionRepository.java`
- ✅ `ClienteRepository.java`
- ✅ `ProductoRepository.java`
- ✅ `LoteRepository.java`
- ✅ `ComprobanteRepository.java`
- ✅ `TipoComprobanteRepository.java`

---

## 🎯 Próximos Pasos

1. **Reiniciar el servidor** para cargar los nuevos controladores
2. **Probar cada endpoint** con Postman
3. **Conectar con el frontend** React/Vue

---

## 🔄 Para Reiniciar el Servidor

1. Presiona `Ctrl + C` en la terminal donde está corriendo
2. Ejecuta nuevamente:
```bash
.\mvnw.cmd spring-boot:run
```

¡Todos los endpoints estarán disponibles! 🚀
