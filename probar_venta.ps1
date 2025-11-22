# Script para probar el endpoint de ventas

# Datos de la venta
$ventaData = @{
    idCliente = 1
    idTipoComprobante = 2
    serie = "B001"
    productos = @(
        @{
            idProducto = 1
            cantidad = 5
        }
    )
} | ConvertTo-Json -Depth 3

# Realizar la petición POST
$response = Invoke-WebRequest `
    -Uri "http://localhost:8080/api/ventas" `
    -Method POST `
    -ContentType "application/json" `
    -Body $ventaData

# Mostrar la respuesta
Write-Host "Status Code: $($response.StatusCode)" -ForegroundColor Green
Write-Host "Response:" -ForegroundColor Cyan
$response.Content | ConvertFrom-Json | ConvertTo-Json -Depth 10
