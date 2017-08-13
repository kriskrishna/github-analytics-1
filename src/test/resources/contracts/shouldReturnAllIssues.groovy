import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method GET()
        url "/issues"
    }
    response {
        status 200
        body([
                [
                        userName: "foo",
                        repository: "spring-cloud/bar"
                ],
                [
                        userName: "bar",
                        repository: "spring-cloud/baz"
                ]
        ])
    }
}