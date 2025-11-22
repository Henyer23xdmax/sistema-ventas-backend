# Test POST with detailed error output
Write-Host "Testing POST endpoints with error details..." -ForegroundColor Cyan
Write-Host ""

# Test POST Categoria
Write-Host "1. POST /api/categorias" -ForegroundColor Yellow
try {
    $body = '{"nombre":"Test POST Categoria"}'
    $response = Invoke-WebRequest -Uri "http://localhost:8080/api/categorias" -Method POST -ContentType "application/json" -Body $body
    Write-Host "✅ SUCCESS - Status: $($response.StatusCode)" -ForegroundColor Green
    $response.Content | Write-Host -ForegroundColor Gray
}
catch {
    Write-Host "❌ ERROR - Status: $($_.Exception.Response.StatusCode.value__)" -ForegroundColor Red
    $reader = New-Object System.IO.StreamReader($_.Exception.Response.GetResponseStream())
    $reader.BaseStream.Position = 0
    $reader.DiscardBufferedData()
    $responseBody = $reader.ReadToEnd()
    Write-Host $responseBody -ForegroundColor Yellow
}
Write-Host ""

# Test POST Marca
Write-Host "2. POST /api/marcas" -ForegroundColor Yellow
try {
    $body = '{"nombre":"Test POST Marca","descripcion":"Test descripcion"}'
    $response = Invoke-WebRequest -Uri "http://localhost:8080/api/marcas" -Method POST -ContentType "application/json" -Body $body
    Write-Host "✅ SUCCESS - Status: $($response.StatusCode)" -ForegroundColor Green
    $response.Content | Write-Host -ForegroundColor Gray
}
catch {
    Write-Host "❌ ERROR - Status: $($_.Exception.Response.StatusCode.value__)" -ForegroundColor Red
    $reader = New-Object System.IO.StreamReader($_.Exception.Response.GetResponseStream())
    $reader.BaseStream.Position = 0
    $reader.DiscardBufferedData()
    $responseBody = $reader.ReadToEnd()
    Write-Host $responseBody -ForegroundColor Yellow
}
Write-Host ""

# Test POST Cliente
Write-Host "3. POST /api/clientes" -ForegroundColor Yellow
try {
    $body = '{"nombre":"Test","apellidos":"Cliente","nroDocumento":"88888888","direccion":"Test 123","celular":"999999999","estado":true}'
    $response = Invoke-WebRequest -Uri "http://localhost:8080/api/clientes" -Method POST -ContentType "application/json" -Body $body
    Write-Host "✅ SUCCESS - Status: $($response.StatusCode)" -ForegroundColor Green
    $response.Content | Write-Host -ForegroundColor Gray
}
catch {
    Write-Host "❌ ERROR - Status: $($_.Exception.Response.StatusCode.value__)" -ForegroundColor Red
    $reader = New-Object System.IO.StreamReader($_.Exception.Response.GetResponseStream())
    $reader.BaseStream.Position = 0
    $reader.DiscardBufferedData()
    $responseBody = $reader.ReadToEnd()
    Write-Host $responseBody -ForegroundColor Yellow
}
Write-Host ""

Write-Host "Tests completed!" -ForegroundColor Cyan
