set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    DO \$\$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'cherhy') THEN
            CREATE DATABASE cherhy;
        END IF;
    END
    \$\$;

    DO \$\$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'payment') THEN
            CREATE DATABASE payment;
        END IF;
    END
    \$\$;

    DO \$\$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'schedule') THEN
            CREATE DATABASE schedule;
        END IF;
    END
    \$\$;

    DO \$\$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'stream') THEN
            CREATE DATABASE stream;
        END IF;
    END
    \$\$;

    DO \$\$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'user') THEN
            CREATE DATABASE user;
        END IF;
    END
    \$\$;

    GRANT ALL PRIVILEGES ON DATABASE cherhy TO postgres;
    GRANT ALL PRIVILEGES ON DATABASE payment TO postgres;
    GRANT ALL PRIVILEGES ON DATABASE schedule TO postgres;
    GRANT ALL PRIVILEGES ON DATABASE stream TO postgres;
    GRANT ALL PRIVILEGES ON DATABASE user TO postgres;
EOSQL
