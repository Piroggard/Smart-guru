# .git/hooks/pre-commit.ps1

# Запуск проверки Checkstyle
mvn checkstyle:check -fae

# Убедитесь, что команда выполнилась успешно
if ($LASTEXITCODE -ne 0) {
    Write-Host "Checkstyle errors detected. Commit aborted."
    exit 1
}

Write-Host "No Checkstyle errors. Proceeding with commit."
exit 0
