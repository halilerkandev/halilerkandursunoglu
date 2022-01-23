echo "Stopping and removing containers.."
docker rm -f \
  patika-postgres
docker rm -f \
  patika-redis