# Debug Cliente POST
$body = '{"nombre":"Test","apellidos":"Cliente","nroDocumento":"99999999","direccion":"Test","celular":"999999999","estado":true}'

Write-Host "Testing POST /api/clientes" -ForegroundColor Cyan
Write-Host "Body: $body" -ForegroundColor Gray
Write-Host ""

try {
    $response = Invoke-WebRequest -Uri "http://localhost:8080/api/clientes" -Method POST -ContentType "application/json" -Body $body -UseBasicParsing
    Write-Host "SUCCESS!" -ForegroundColor Green
    $response.Content
}
catch {
    Write-Host "ERROR!" -ForegroundColor Red
    Write-Host "Status: $($_.Exception.Response.StatusCode.value__)" -ForegroundColor Yellow
    
    $result = $_.Exception.Response.GetResponseStream()
    $reader = New-Object System.IO.StreamReader($result)
    $reader.BaseStream.Position = 0
    $reader.DiscardBufferedData()
    $responseBody = $reader.ReadToEnd()
    
    Write-Host "Response:" -ForegroundColor Yellow
    $responseBody | ConvertFrom-Json | ConvertTo-Json -Depth 10
}
