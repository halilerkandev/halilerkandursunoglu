echo "Starting postgres container.."
docker run --name patika-postgres \
  -e POSTGRES_USER=patika-user \
  -e POSTGRES_PASSWORD=patika-password \
  -d \
  -p 5432:5432 \
  postgres
echo "Starting redis container.."
docker run --name patika-redis \
  -p 6379:6379 \
  -d \
  redis