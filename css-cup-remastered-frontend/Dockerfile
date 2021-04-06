FROM node:alpine AS builder
WORKDIR /app
COPY . .
RUN npm ci && npm run build

FROM nginx:alpine
COPY --from=builder /app/dist/css-cup-remastered-frontend /usr/share/nginx/html
EXPOSE 80
