

```bash
docker run --name ds-lab-pg --rm \
-e POSTGRES_PASSWORD=pass123 \
-e POSTGRES_USER=dbuser \
-e POSTGRES_DB=students \
-d --net=host \
-v pgdata:/var/lib/postgresql/data \
postgres:14
```