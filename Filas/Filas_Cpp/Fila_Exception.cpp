#include <stdexcept>
#include <string>

class FilaException : public std::runtime_error {
public:
    explicit FilaException(const std::string& message) : std::runtime_error(message) {}
};